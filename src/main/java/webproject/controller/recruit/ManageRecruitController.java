 package webproject.controller.recruit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import webproject.controller.base.BaseController;
import webproject.mapper.MenuMapper;
import webproject.mapper.UserMapper;
import webproject.model.ResultBean;
import webproject.model.system.MenuVo;
import webproject.model.system.User;
import webproject.service.MenuService;
import webproject.utils.AdminUtil;
import webproject.utils.MenuUtil;


/**
 * 
* @author hts
* @version date：2017年11月9日 下午7:32:38 
*
 */
@Controller
@RequestMapping("/recruit")
public class ManageRecruitController extends BaseController {
	@Autowired
	MenuService menuService;

	@RequestMapping("")
	public String menu(Model model) {
		List list = menuService.findAllMenus();
		model.addAttribute("menus", list);
		return "menumanage";
	}

	@RequestMapping("/init.json")
	@ResponseBody
	public Map<String, Object> tableinit(int limit, int offset, Model model) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List pagelist = menuService.findAllMenusByPage(limit,offset);
		map.put("rows", pagelist);
		map.put("total", menuService.findMenuCount());
		return map;
	}

	@RequestMapping("/delete.json")
	@ResponseBody
	@RequiresRoles("super")
	public ResultBean delete(Model model, @RequestParam("ids[]") List<String> ids) throws Exception {

		ResultBean result = new ResultBean();
		result.setStatus(200);
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			if (!menuService.deleteMenu(id)) {
				result.setStatus(500);
				result.setMsg(result.getMsg() + "\nid为" + id + "的菜单不是叶子节点不可删除，请先删除其子节点");
			}
		}
		if (result.getStatus() == 200)
			result.setMsg("删除成功");
		return result;
	}

	@RequestMapping("/update.json")
	@ResponseBody
	@RequiresRoles("super")
	public ResultBean update(Model model,  MenuVo menuVo,int updatemode) throws Exception {
		ResultBean result = new ResultBean();
		menuService.updateOrInsert(menuVo,updatemode);
		return responseMsg("更新成功",200,null);
	}

}
