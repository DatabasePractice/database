package webproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import webproject.model.PageData;

/** 
* @author hts
* @version date：2017年8月19日 下午9:09:29 
* 
*/
@Service
public interface AuthorizeService {
void insertRoleAndAuthorize(PageData pageData,List<Map> menuList);
void updateRoleAndAuthorize(PageData pageData,List<Map> menulist);
void deleteRole(String id);
Object findRoleCount();
List findAllRoles();
List findRoleAuthorize(String roleid);
}
