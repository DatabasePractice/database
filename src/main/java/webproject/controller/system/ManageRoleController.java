package webproject.controller.system;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;

import webproject.controller.base.BaseController;
import webproject.mapper.MenuMapper;
import webproject.model.PageData;
import webproject.model.system.User;
import webproject.service.AuthorizeService;
import webproject.utils.AdminUtil;
import webproject.utils.MenuUtil;

/**
 *  
 * 
 * @author hts
 * @version date：2017年7月14日 下午12:36:00 
 * 
 */
@RequestMapping("authorize")
@Controller
public class ManageRoleController extends BaseController {
	@Autowired
	AuthorizeService authorizeService;
	@Autowired
	MenuMapper menumapper;

	@RequestMapping("/init.json")
	@ResponseBody
	public Map<String, Object> tableinit(int limit, int offset, Model model) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		int pageNum = offset / limit + 1;
		PageHelper.startPage(pageNum, limit);

		List pagelist = authorizeService.findAllRoles();
		map.put("rows", pagelist);
		map.put("total", authorizeService.findRoleCount());
		return map;
	}

	@RequestMapping("/ztreeinit.json")
	@ResponseBody
	public Map<String, Object> zTreeinit(String roleid, Model model) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List list = menumapper.findAllMenus();
		List authlist = authorizeService.findRoleAuthorize(roleid);
		List<Map> zNodes = MenuUtil.menuAuthorize(list, authlist);
		map.put("zNodes", zNodes);
		return map;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(Model model, @RequestParam("ids[]") List<String> ids) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			if (AdminUtil.isSuperByRoleId(id)) {
				map.put("msg", "超级管理员不能删除");
				break;
			}

			authorizeService.deleteRole(id);
		}
		map.put("msg", "删除成功");
		return map;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update() {
		PageData pageData = this.getPageData();
		String updatemode= (String) pageData.get("updatemode");
		String menuListstr= (String) pageData.get("menuList");
		List<Map> menuList=new Gson().fromJson(menuListstr, List.class);	
		Map<String, Object> map = new LinkedHashMap<String, Object>();	
		// 更新操作
		if (updatemode.equals("0")) {
			try {
				authorizeService.updateRoleAndAuthorize(pageData, menuList);			
				map.put("msg", "修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", e.toString());
			}
			
		}
		// 增加操作
		if (updatemode.equals("1")) {
			
			try {
				authorizeService.insertRoleAndAuthorize(pageData, menuList);
				map.put("msg", "新增成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", e.toString());
			}
		}

		return map;
	}
}
