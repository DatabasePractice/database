package webproject.controller.system;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;

import ch.qos.logback.classic.Logger;
import webproject.controller.base.BaseController;
import webproject.mapper.RoleMapper;
import webproject.mapper.UserMapper;
import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.model.system.Role;
import webproject.model.system.User;
import webproject.service.AuthorizeService;
import webproject.service.UserService;
import webproject.service.UserphotoService;
import webproject.utils.AdminUtil;

/**
 *  
 * 
 * @author hts
 * @version date：2017年4月15日 下午4:09:02 
 * 
 */
@Controller
@RequestMapping("system")
public class ManageUserConroller extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	AuthorizeService authorizeService;
	@Autowired
	UserphotoService userphotoService;
	@RequestMapping("")
	public String toUserTable(Model model) {
		List<Role> rolelist = authorizeService.findAllRoles();
		model.addAttribute("rolelist", rolelist);
		return "usertable";
	}

	@RequestMapping("/test")
	public String toTable(Model model) {

		return "test";
	}

	@RequestMapping("/userTableInit")
	@ResponseBody
	public Map<String, Object> tableinit(int limit, int offset, Model model) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		int pageNum = offset / limit + 1;
		PageHelper.startPage(pageNum, limit);
		List list = userService.findUserAndRole(null);
		map.put("rows", list);
		map.put("total", userService.findusercount());
		return map;
	}

	@RequestMapping("/update")
	@ResponseBody
	@RequiresRoles("super")
	public ResultBean update(Model model, String name, String account, int updatemode, String password, String role,
			String id) throws Exception {
		User user = new User(name, account, password, role, id);
		return userService.updateOrInsert(user, updatemode);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@RequiresRoles("super")
	public ResultBean delete(Model model, @RequestParam("ids[]") List<String> ids) throws Exception {
		return userService.BatchDeleteUser(ids);
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public ResultBean updatePassword(Model model, String password, String oldpassword) throws Exception {
		String username = getUsername();

		return userService.updatePassword(username, password, oldpassword);
	}

	@RequestMapping("/updateAvater")
	@ResponseBody
	public Result upload(HttpServletRequest request) throws Exception {
		String contentType = request.getContentType();
		Result result = new Result();
		result.avatarUrls = new ArrayList();
		result.success = false;
		result.msg = "Failure!";
		PageData pd=this.getPageData();
		pd.put("USERNAME", this.getUsername());
		if (contentType.indexOf("multipart/form-data") >= 0) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator iterator = multipartRequest.getFileNames();
			// 定义一个变量用以储存当前头像的序号
			int avatarNumber = 1;
			// 取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
			String fileName = simpleDateFormat.format(new Date());
			Random random = new Random();
			String randomCode = "";
			for (int i = 0; i < 8; i++) {
				randomCode += Integer.toString(random.nextInt(36), 36);
			}
			fileName = fileName + randomCode;
			// 基于原图的初始化参数
			String initParams = "";
			BufferedInputStream inputStream;
			BufferedOutputStream outputStream;
			// 遍历表单域
			while (iterator.hasNext()) {
				String filename = iterator.next().toString();
				MultipartFile mfile = multipartRequest.getFile(filename);
				logger.info(filename);
				logger.info(mfile.getOriginalFilename());
				Boolean isSourcePic = filename.equals("__source");
				if (isSourcePic || filename.startsWith("__avatar")) {
					String virtualPath = "uploadFiles/uploadUserPhoto/jsp_avatar" + avatarNumber + "_" + fileName
							+ ".jpg";
					// 原始图片（默认的 file
					// 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）。
					if (isSourcePic) {
						// 文件名，如果是本地或网络图片为原始文件名、如果是摄像头拍照则为 *FromWebcam.jpg
						String sourceFileName = mfile.getOriginalFilename();
						// 原始文件的扩展名(不包含“.”)
						String sourceExtendName = sourceFileName.substring(sourceFileName.lastIndexOf('.') + 1);
						result.sourceUrl = virtualPath = String.format("uploadFiles/uploadUserPhoto/jsp_source_%s.%s",
								fileName, sourceExtendName);
						pd.put("PHOTO0", virtualPath);
					}
					// 头像图片（默认的 file
					// 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names）。
					else {
						result.avatarUrls.add(virtualPath);
						avatarNumber++;
						pd.put("PHOTO1", virtualPath);
					}
					//确保上传路径存在
					File uploadDir=new File(ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/public/uploadFiles/uploadUserPhoto");
					if(!uploadDir.exists())
						uploadDir.mkdirs();
					inputStream = new BufferedInputStream(mfile.getInputStream());
					outputStream = new BufferedOutputStream(
							new FileOutputStream(ClassUtils.getDefaultClassLoader().getResource("").getPath() +"/public/"+ virtualPath));
					Streams.copy(inputStream, outputStream, true);
					inputStream.close();
					outputStream.flush();
					outputStream.close();
				}
			}
			userphotoService.savephoto(pd);
			result.success = true;
			result.msg = "Success!";
			return result;
		}

		return result;
	}

	public class Result {
		public Boolean success;
		public String msg;
		public String sourceUrl;
		public ArrayList avatarUrls;
	}
}
