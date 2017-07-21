package webproject.web;

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

import webproject.mapper.MenuMapper;
import webproject.mapper.UserMapper;
import webproject.model.MenuVo;
import webproject.model.User;
import webproject.utils.AdminUtil;
import webproject.utils.MenuUtil;

/**
 *  
 * 
 * @author hts
 * @version date：2017年7月18日 上午11:44:12 
 * 
 */
@Controller
@RequestMapping("/menu")
public class manageMenuController {
	@Autowired
	MenuMapper mapper;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/init.json")
	@ResponseBody
	public Map<String, Object> tableinit(int limit, int offset, Model model) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		int pageNum = offset / limit + 1;
		PageHelper.startPage(pageNum, limit);

		List pagelist = mapper.findAllMenus();
		MenuUtil.MenuProcess(pagelist);
		map.put("rows", pagelist);
		map.put("total", mapper.findMenuCount());
		logger.info("table init");
		return map;
	}

	@RequestMapping("/delete.json")
	@ResponseBody
	@RequiresRoles("super")
	public Map<String, Object> delete(Model model, @RequestParam("ids[]") List<String> ids) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("msg", "删除成功");
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			try {
				mapper.deleteMenu(id);
			} catch (Exception e) {
              logger.warn(e.toString());
              map.put("msg", "不是叶子节点的menu不可删除，请先删除其子节点");
			}
		}
		
		return map;
	}

	@RequestMapping("/update.json")
	@ResponseBody
	@RequiresRoles("super")
	public Map<String, Object> update(Model model, int updatemode, MenuVo menuVo) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			MenuUtil.insertPromise(menuVo,mapper);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			e.printStackTrace();
			return map;
		}
		// 更新操作
		if (updatemode == 0) {

			try {

				mapper.updateMenu(menuVo);
				map.put("msg", "修改成功");
			} catch (Exception e) {
				logger.warn(e.getMessage());
				map.put("msg", e.getMessage());
				e.printStackTrace();

			}
		}
		// 增加操作
		if (updatemode == 1) {
			
			try {
				mapper.insertMenu(menuVo);
				map.put("msg", "新增成功");
			} catch (Exception e) {
				logger.warn(e.getMessage());
				map.put("err", e.getMessage());
				e.printStackTrace();
			}
			
		}

		return map;
	}

}
