package webproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import webproject.model.PageData;

/** 
* @author hts
* @version date：2017年8月19日 下午9:09:29 
* 
*/
@Service
public interface BillQueryService {
	PageData findAll(PageData pd);
}
