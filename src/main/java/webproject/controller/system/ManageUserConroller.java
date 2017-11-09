package webproject.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;

import ch.qos.logback.classic.Logger;
import webproject.controller.base.BaseController;
import webproject.mapper.RoleMapper;
import webproject.mapper.UserMapper;
import webproject.model.ResultBean;
import webproject.model.system.Role;
import webproject.model.system.User;
import webproject.service.AuthorizeService;
import webproject.service.UserService;
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
	
	@RequestMapping("")
	public String toUserTable(Model model) {
		List<Role> rolelist=authorizeService.findAllRoles();
		model.addAttribute("rolelist",rolelist);
		return "usertable";
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
	public ResultBean update(Model model, String name, String account, int updatemode, String password,
			String role, String id) throws Exception {
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
	public ResultBean updatePassword(Model model, String password,String oldpassword) throws Exception {
		String username=getUsername();
		
		return userService.updatePassword(username, password,oldpassword);
	}
}
