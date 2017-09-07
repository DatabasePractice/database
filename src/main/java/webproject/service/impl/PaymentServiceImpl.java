package webproject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	PaymentMapper rolemapper;


}
