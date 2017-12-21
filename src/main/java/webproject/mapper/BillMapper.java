package webproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import webproject.model.PageData;

/** 
* @author hts
* @version date：2017年10月27日 下午8:21:06 
* 
*/
@Mapper
public interface BillMapper {
void save(PageData pd);
List<PageData> queryProjectPage(PageData pd);
int countOfdatalistPage(PageData pd);
void updateBill(PageData pd);
List<PageData> findByCustomId(PageData pd);
}
