package webproject.service;

import java.io.FileInputStream;

import webproject.model.PageData;

/** 
* @author hts
* @version date：2017年10月26日 下午8:52:05 
* 
*/
public interface BillService {
	void  importBillByExcel(FileInputStream fi) throws Exception;
	void  importBill(PageData pd);
}
