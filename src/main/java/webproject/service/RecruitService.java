package webproject.service;

import java.io.FileInputStream;
import java.util.List;

import webproject.model.PageData;


public interface RecruitService{

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
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public PageData list(PageData pd)throws Exception;

	void importByExcel(FileInputStream fi) throws Exception;
	
		
	
}

