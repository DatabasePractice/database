package webproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import webproject.model.PageData;

/** 
* @author hts
* @version date：2017年10月27日 下午8:21:06 
* 
*/
@Mapper
public interface BillMapper {
void save(PageData pd);
}
