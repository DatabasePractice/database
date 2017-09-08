package webproject.service;

import java.util.List;

import webproject.model.system.MenuVo;

/** 
* @author hts
* @version date：2017年9月8日 下午11:26:12 
* 
*/
public interface MenuService {

	List<MenuVo> findAllMenus();

	List<MenuVo> findAllMenusByPage(int limit, int offset);

	int findMenuCount();

	boolean deleteMenu(String id) throws Exception;

	boolean updateOrInsert(MenuVo menuVo, int updatemode) throws Exception;

}