package webproject.service;

import java.util.List;

import webproject.model.PageData;

/** 
* @author hts
* @version date：2017年12月6日 上午12:04:51 
* 
*/
public interface UserphotoService {
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	

	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	
	public void savephoto(PageData pd) throws Exception;
}
