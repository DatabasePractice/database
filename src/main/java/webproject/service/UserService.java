package webproject.service;

import java.util.List;

import webproject.model.ResultBean;
import webproject.model.system.MenuVo;
import webproject.model.system.Role;
import webproject.model.system.User;

/** 
* @author hts
* @version date：2017年9月8日 下午10:34:10 
* 
*/
public interface UserService {

	List<MenuVo> findmenus(User user);

	Role findRole(User user);

	List<User> findUser(User user);

	ResultBean doLogin(User user);

	int findusercount();

	List findUserAndRole(User user);

	ResultBean updateOrInsert(User user, int updatemode) throws Exception;

	ResultBean BatchDeleteUser(List<String> ids) throws Exception;

}