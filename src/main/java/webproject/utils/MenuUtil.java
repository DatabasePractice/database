package webproject.utils;
/** 
* @author hts
* @version date：2017年7月18日 上午10:43:30 
* 
*/

import static org.mockito.Matchers.intThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import webproject.mapper.MenuMapper;
import webproject.mapper.UserMapper;
import webproject.model.MenuVo;

@Component
public class MenuUtil {
	static String rootMenuID = "100000";

	/**
	 * 
	 * @param list
	 */
	private static String pname;

	/**
	 * 传入的maplist以level从低到高排序，所以某一结点的parent一定在该节点之前。 该方法目的是将菜单的路径还原成中文。如
	 * 系统管理-角色管理
	 * 
	 * @param list
	 */
	public static void MenuProcess(List<MenuVo> list) {

		for (MenuVo menua : list) {
			StringBuilder sb = new StringBuilder(menua.getMenuname());
			for (MenuVo menub : list) {

				if (menua.getParent().equals(menub.getMenuid()))

					sb.insert(0, menub.getProcessname() + "-");

			}
			menua.setProcessname(sb.toString());
		}

	}

	/**
	 * 对菜单进行增加和更新必须要对level和isleaf进行更新，用户递交的menuvo的这两项数据是不可靠的
	 * 
	 * @param menuVo
	 */
	public static void insertPromise(MenuVo menuVo, MenuMapper menumapper) throws RuntimeException {
		if (menuVo.getParent().equals(menuVo.getMenuid()))
			throw new RuntimeException("修改的菜单不能parent是其本身");
		// root菜单不能进行修改，只能设定其名字
		if (menuVo.getMenuid().equals(rootMenuID)) {
			menuVo.setIsleaf(false);
			menuVo.setLevel(0);
			menuVo.setParent(null);
			menuVo.setRequest(null);
			return;
		}
		if (menuVo.getParent() == null)
			throw new RuntimeException("菜单必须拥有上级菜单");
		MenuVo parentmenu = menumapper.findMenuById(menuVo.getParent());
		if (parentmenu == null) {
			throw new RuntimeException("系统错误：上级菜单不存在");
		} else {
			menuVo.setLevel(parentmenu.getLevel() + 1);
			// 如果更新菜单的上级菜单原本为leaf则取消其leaf状态
			if (parentmenu.getIsleaf()) {
				parentmenu.setIsleaf(false);
				menumapper.updateMenu(menuVo);
			}
		}
		if (menumapper.findSonMenuCount(menuVo.getMenuid()) > 0)
			menuVo.setIsleaf(false);
		else
			menuVo.setIsleaf(true);
	}

	public static List<Map> menuAuthorize(List<MenuVo> list, List<MenuVo> authlist) {
		// TODO Auto-generated method stub
		List<Map> plist=new ArrayList<Map>();
		for(MenuVo menu:list)
		{ Map map=new HashMap();
		  map.put("checked", false);
		  map.put("menuid", menu.getMenuid());
		  map.put("menuname", menu.getMenuname());
		  map.put("parent", menu.getParent());
			for(MenuVo authMenu:authlist)
			{if(menu.getMenuid().equals(authMenu.getMenuid()))
				map.put("checked", true);
			}
			plist.add(map);
		}
		return plist;
	}

}