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

import webproject.mapper.BillMapper;
import webproject.mapper.CustomMapper;
import webproject.model.PageData;
import webproject.service.BillService;
import webproject.service.CustomService;
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
public class CustomServiceImpl implements CustomService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CustomMapper customMapper;

	@Override
	public PageData queryCustom(PageData pd) {
		// TODO Auto-generated method stub
		return customMapper.datalistPage(pd);
	}

}
