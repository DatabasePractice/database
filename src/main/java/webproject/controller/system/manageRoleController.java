package webproject.controller.system;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import webproject.mapper.MenuMapper;
import webproject.mapper.RoleMapper;
import webproject.utils.MenuUtil;

/** 
* @author hts
* @version date：2017年7月14日 下午12:36:00 
* 
*/
@RequestMapping("authorize")
@Controller
public class manageRoleController {
	@Autowired
	RoleMapper rolemapper;
	@Autowired
	MenuMapper menumapper;
	@RequestMapping("/init.json")
	@ResponseBody
	public Map<String, Object> tableinit(int limit, int offset, Model model) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		int pageNum = offset / limit + 1;
		PageHelper.startPage(pageNum, limit);

		List pagelist = rolemapper.findAllRoles();
		map.put("rows", pagelist);
		map.put("total", rolemapper.findRoleCount());
		return map;
	}
	@RequestMapping("/ztreeinit.json")
	@ResponseBody
	public Map<String, Object> zTreeinit(String roleid, Model model) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		 List list=menumapper.findAllMenus();
		 List authlist=rolemapper.findRoleAuthorize(roleid);
		 List<Map> zNodes=MenuUtil.menuAuthorize(list,authlist);
		 map.put("zNodes", zNodes);
		return map;
	}
	
}
