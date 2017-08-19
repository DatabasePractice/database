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
import webproject.model.system.Role;
import webproject.model.system.User;
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
public class manageUserConroller extends BaseController {
	@Autowired
	UserMapper mapper;
	@Autowired
	RoleMapper roleMapper;
	
	@RequestMapping("")
	public String toUserTable(Model model) {
		List<Role> rolelist=roleMapper.findAllRoles();
		model.addAttribute("rolelist",rolelist);
		return "usertable";
	}
	
	@RequestMapping("/userTableInit")
	@ResponseBody
	public Map<String, Object> tableinit(int limit, int offset, Model model) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		int pageNum = offset / limit + 1;
		PageHelper.startPage(pageNum, limit);
		List list = mapper.findUserAndRole(null);
		map.put("rows", list);
		map.put("total", mapper.findusercount());
		return map;
	}

	@RequestMapping("/update")
	@ResponseBody
	@RequiresRoles("super")
	public Map<String, Object> update(Model model, String name, String account, int updatemode, String password,
			String role, String id) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (AdminUtil.isSuperById(id)) {
			map.put("err", "超级管理员不能修改");
			return map;
		}

		// 更新操作
		if (updatemode == 0) {
			User user = new User(name, account, password, role, id);
			try {
				mapper.updateUser(user);
			} catch (Exception e) {
				System.out.println(e.toString());
				map.put("err", e.toString());
			}
			map.put("msg", "修改成功");
		}
		// 增加操作
		if (updatemode == 1) {
			User user = new User(name, account, password, role, null);
			try {
				mapper.insertUser(user);
			} catch (Exception e) {
				System.out.println(e.toString());
				map.put("err", e.toString());
			}
			map.put("msg", "新增成功");
		}

		return map;
	}

	@RequestMapping("/delete")
	@ResponseBody
	@RequiresRoles("super")
	public Map<String, Object> delete(Model model, @RequestParam("ids[]") List<String> ids) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			if (AdminUtil.isSuperById(id)) {
				map.put("msg", "超级管理员不能删除");
				break;
			}

			mapper.deleteUser(id);
		}
		map.put("msg", "删除成功");
		return map;
	}
}
