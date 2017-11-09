package webproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import webproject.model.system.MenuVo;
import webproject.model.system.Role;
import webproject.model.system.User;

/** 
* @author hts
* @version date：2017年4月11日 下午7:10:38 
* 
*/
@Mapper
public interface UserMapper {
boolean insertUser(User user);
boolean updateUser(User user);
List<User> findUser(User user);
List<Map> findUserAndRole(User user);
Role findRole(User user);
List<MenuVo> findparentmenu(User user);
List<MenuVo> findsonmenu(User user);
List<MenuVo> findmenus(User user);
int findsameaccount(User user);
int findusercount();
void deleteUser(String id);
void updatePasswordByAccount(User user);
}
