package webproject.service.impl;

import java.io.FileInputStream;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import webproject.mapper.RecruitMapper;
import webproject.model.PageData;
import webproject.service.RecruitService;
import webproject.utils.Const;
import webproject.utils.ObjectExcelRead;


/** 
 * 说明： aa
 * 创建人：FH Q313596790
 * 创建时间：2017-11-10
 * @version
 */
@Service
public class RecruitServiceImpl implements RecruitService{
@Autowired
	RecruitMapper recruitMapper;
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		recruitMapper.save( pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		recruitMapper.delete(pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		recruitMapper.edit( pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public PageData list(PageData pd)throws Exception{
		int offset= pd.getAsInt("offset");
		int limit= pd.getAsInt("limit");
		int pageNum = offset / limit + 1;
		PageData returnpd=new PageData();
		PageHelper.startPage(pageNum, limit);
		returnpd.put("rows",recruitMapper.datalistPage(pd));
		int totalcount=recruitMapper.datalistPageCount(pd);
		returnpd.put("total",totalcount);
		return returnpd;	
	}
	
	@Override
	public void importByExcel(FileInputStream fi) throws Exception {
		List vars = ObjectExcelRead.readExcel(fi, 1, 0, 0);
		for (int i = 0; i < vars.size(); i++) {
			PageData pd = (PageData) vars.get(i);
			for (int j = 0;; j++) {
				String var = (String) pd.get("var" + j);
				if (var == null)
					break;
				pd.put(Const.excelConstant[j], var);
			}
			save(pd);
		}
	}

	
	
	
}

