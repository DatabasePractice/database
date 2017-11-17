package webproject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import webproject.mapper.PaymentMapper;
import webproject.mapper.RoleMapper;
import webproject.model.PageData;
import webproject.service.AuthorizeService;
import webproject.service.PaymentService;

/**
 *  
 * 
 * @author hts
 * @version date：2017年8月19日 下午9:09:07 
 * 
 */
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentMapper payDao;

	public PageData findAll(PageData pd) {
		int offset = pd.getAsInt("offset");
		int limit = pd.getAsInt("limit");
		int pageNum = offset / limit + 1;
		PageData returnpd = new PageData();
		PageHelper.startPage(pageNum, limit);
		returnpd.put("rows", payDao.datalistPage(pd));
		int totalcount = payDao.countOfdatalistPage(pd);
		returnpd.put("total", totalcount);
		return returnpd;
	}

}
