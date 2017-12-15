package webproject.service.impl;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import webproject.mapper.BillMapper;
import webproject.model.PageData;
import webproject.service.BillService;
import webproject.utils.Const;
import webproject.utils.ObjectExcelRead;

/**
 *  
 * 
 * @author hts
 * @version date：2017年10月26日 下午8:48:06 
 * 
 */
@Transactional
@Service
public class BillServiceImpl implements BillService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BillMapper billMapper;

	@Override
	public void importBillByExcel(FileInputStream fi) throws Exception {
		List vars = ObjectExcelRead.readExcel(fi, 1, 0, 0);
		logger.info(vars.toString());
		for (int i = 0; i < vars.size(); i++) {
			PageData pd = (PageData) vars.get(i);
			for (int j = 0;; j++) {
				String var = (String) pd.get("var" + j);
				if (var == null)
					break;
				pd.put(Const.excelConstant[j], var);
			}
			importBill(pd);
		}
	}

	@Override
	public void importBill(PageData pd) {
		// TODO Auto-generated method stub
		Calendar calendar=Calendar.getInstance();
		pd.put("CREATETIME", calendar.getTime());
        billMapper.save(pd);
	}
	
	public PageData queryProject(PageData pd) {
		int offset = pd.getAsInt("offset");
		int limit = pd.getAsInt("limit");
		int pageNum = offset / limit + 1;
		PageData returnpd = new PageData();
		PageHelper.startPage(pageNum, limit);
		returnpd.put("rows", billMapper.queryProjectPage(pd));
		int totalcount = billMapper.countOfdatalistPage(pd);
		returnpd.put("total",totalcount);//替换下一条
		//returnpd.put("total",1);
		return returnpd;
	}

}
