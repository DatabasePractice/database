package webproject.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import webproject.mapper.MenuMapper;
import webproject.mapper.RoleMapper;
import webproject.mapper.UserMapper;
import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.model.system.MenuVo;
import webproject.model.system.Role;
import webproject.model.system.User;
import webproject.service.AuthorizeService;
import webproject.service.MenuService;
import webproject.utils.MenuUtil;

/**
 *  
 * 
 * @author hts
 * @version date：2017年8月19日 下午9:09:07 
 * 
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuMapper menuMapper;

	/* (non-Javadoc)
	 * @see webproject.service.impl.MenuService#findAllMenus()
	 */
	@Override
	public List<MenuVo> findAllMenus() {
		List<MenuVo> menus = menuMapper.findAllMenus();
		MenuUtil.MenuProcess(menus);
		return menus;
	}
	
	/* (non-Javadoc)
	 * @see webproject.service.impl.MenuService#findAllMenusByPage(int, int)
	 */
	@Override
	public List<MenuVo> findAllMenusByPage(int limit,int offset) {
		int pageNum = offset / limit + 1;
		PageHelper.startPage(pageNum, limit);
		List<MenuVo> menus = menuMapper.findAllMenus();
		MenuUtil.MenuProcess(menus);
		return menus;
	}

	/* (non-Javadoc)
	 * @see webproject.service.impl.MenuService#findMenuCount()
	 */
	@Override
	public int findMenuCount() {
		return menuMapper.findMenuCount();
	}

	/* (non-Javadoc)
	 * @see webproject.service.impl.MenuService#DeleteMenu(java.lang.String)
	 */
	@Override
	public boolean deleteMenu(String id) throws Exception {
		MenuVo menuVo = menuMapper.findMenuById(id);
		if (menuVo.getIsleaf() == false)
			return false;
		else {
			menuMapper.deleteMenu(id);
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see webproject.service.impl.MenuService#updateOrInsert(webproject.model.system.MenuVo, int)
	 */
	@Override
	public boolean updateOrInsert(MenuVo menuVo, int updatemode) throws Exception {
		MenuUtil.insertPromise(menuVo, menuMapper);
		// 更新操作
		if (updatemode == 0) {
			menuMapper.updateMenu(menuVo);
		}
		// 增加操作
		if (updatemode == 1) {
			menuMapper.insertMenu(menuVo);
		}
		return true;
	}
}
