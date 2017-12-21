package webproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import webproject.model.PageData;
import webproject.model.system.MenuVo;
import webproject.model.system.Role;
import webproject.model.system.User;

/** 
* @author hts
* @version date：2017年4月11日 下午7:10:38 
* 
*/
@Mapper
public interface CustomMapper {
//	void save(PageData pd);
	void delete(PageData pd);
	void edit(PageData pd);
	PageData findById(PageData pd);
	PageData datalistPage(PageData pd);
	@Update("update  custom set balance=#{newbalance} where custom_id= #{newcustomid} ")
	void updateCustomer(double newbalance, double newcustomid);
	PageData findByTel(String TEL);
}

