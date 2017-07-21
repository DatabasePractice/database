package webproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import webproject.model.MenuVo;

/** 
* @author hts
* @version date：2017年7月18日 上午11:55:17 
* 
*/
@Mapper
public interface MenuMapper {
	@Select( "select a.*,b.menuname as parentname from er_menu a left join er_menu b on a.parent=b.menuid order by a.level asc")
	List<MenuVo> findAllMenus();
	@Select( "select count(*) from er_menu")
	int findMenuCount();
	@Select( "select * from er_menu where menuid=#{menuid}")
	MenuVo findMenuById(String menuid);
	@Select( "select count(*) from er_menu where parent=#{menuid}")
	int findSonMenuCount(String menuid);
	@Delete("delete from er_menu where menuid=#{id}")
	void deleteMenu(String id);
	@Update("update  er_menu set request=#{request},isleaf=#{isleaf},menuname=#{menuname},parent=#{parent},level=#{level} where menuid=#{menuid}")
	void updateMenu(MenuVo menuVo);
	@Insert("insert into er_menu values(null,#{request},#{isleaf},#{menuname},#{parent},#{level})")
	void insertMenu(MenuVo menuVo);
	
}
