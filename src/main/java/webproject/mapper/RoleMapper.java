package webproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import webproject.model.MenuVo;
import webproject.model.Role;

/**
 *  
 * 
 * @author hts
 * @version date：2017年7月19日 下午5:25:49 
 * 
 */
@Mapper
public interface RoleMapper {
	/**
	 * role相关
	 * 
	 * @return
	 */
	@Select("select * from er_role ")
	List<Role> findAllRoles();

	@Select("select count(*) from er_role")
	int findRoleCount();

	@Delete("delete from er_role where roleid=#{id}")
	void deleteRole(String id);

	@Insert("insert into er_role values(null,#{rolename})")
	void insertRole(Role role);

	@Update("update  er_role set rolename=#{rolename} where roleid=#{roleid}")
	void updateRole(Role role);

	/**
	 * 授权相关
	 */
	@Select("select b.* from er_role_authorize a, er_menu b where a.roleid=#{id} and a.menuid= b.menuid")
	List<MenuVo> findRoleAuthorize(String id);
	@Insert("insert into er_role values(null,#{rolename})")
	void insertMenu(Role role);


}
