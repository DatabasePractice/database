package webproject.controller.system;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import webproject.controller.base.BaseController;
import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.service.BillService;
import webproject.service.MenuService;

/** 
* @author hts
* @version date：2017年10月26日 下午9:37:04 
* 
*/
@Controller
@RequestMapping("bill")
public class BillController extends BaseController{
	@Autowired
	BillService billService;
	@RequestMapping("/excelImport")
	public String excelImport(Model model) {
		return "excelImport";
	}
	
	@RequestMapping("/excelImport/doImport")
	@ResponseBody
	public ResultBean doImport(@RequestParam(value="excel",required=false) MultipartFile file) throws Exception {
		
		FileInputStream fi=(FileInputStream)file.getInputStream();
		billService.importBillByExcel(fi);
		return responseMsg("导入成功",200,null);
	}
	
	@RequestMapping("/billquery")
	public String toPaymentTable(Model model) {
		return "billquery";
	}
	
	@RequestMapping("/billquery/TableInit")
	@ResponseBody
	public Map<String, Object> tableinit() {
		PageData pd = this.getPageData();
		//注释掉这三行是为了当所有输入框为空时，查询所有数据，相当于select*
//		if (pd.judgeEmpty("CUSTOM_ID")&&pd.judgeEmpty("TYPE")&&
//				pd.judgeEmpty("BEGINTIME")&&pd.judgeEmpty("ENDTIME"))
//			return new PageData();
		return billService.queryProject(pd);
	}
	
}
