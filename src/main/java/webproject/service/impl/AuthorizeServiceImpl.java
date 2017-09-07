package webproject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webproject.mapper.RoleMapper;
import webproject.model.PageData;
import webproject.service.AuthorizeService;

/**
 *  
 * 
 * @author hts
 * @version date：2017年8月19日 下午9:09:07 
 * 
 */
@Transactional
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
	@Autowired
	RoleMapper rolemapper;

	@Override
	public void insertRoleAndAuthorize(PageData pageData, List<Map> menuList) {
		// TODO Auto-generated method stub
		PageData pd = rolemapper.findRoleByName(pageData);
		if (pd == null) {
			rolemapper.insertRole(pageData);
			pd = rolemapper.findRoleByName(pageData);
			for (Map menumap : menuList) {
				menumap.put("roleid", pd.get("ROLEID"));
				if (menumap.get("checked").equals("true")) {
					rolemapper.insertRoleAuthorize(menumap);
				} else
					rolemapper.deleteRoleAuthorize(menumap);
			}
		}
	}

	@Override
	public void updateRoleAndAuthorize(PageData pageData, List<Map> menuList) {
		// TODO Auto-generated method stub
		rolemapper.updateRole(pageData);
		for (Map menumap : menuList) {
			menumap.put("roleid", (String) pageData.get("roleid"));
			if (menumap.get("checked").equals("true")) {
				rolemapper.insertRoleAuthorize(menumap);
			} else
				rolemapper.deleteRoleAuthorize(menumap);
		}
	}

	@Override
	public void deleteRole(String id) {
		// TODO Auto-generated method stub
		rolemapper.deleteRole(id);
	}

	@Override
	public Object findRoleCount() {
		// TODO Auto-generated method stub
		return rolemapper.findRoleCount();
	}

	@Override
	public List findAllRoles() {
		// TODO Auto-generated method stub
		return rolemapper.findAllRoles();
	}

	@Override
	public List findRoleAuthorize(String roleid) {
		// TODO Auto-generated method stub
		return rolemapper.findRoleAuthorize(roleid);
	}

}
