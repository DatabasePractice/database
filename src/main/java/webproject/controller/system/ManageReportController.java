package webproject.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import webproject.controller.base.BaseController;
import webproject.model.PageData;
import webproject.model.ResultBean;

/** 
* @author hts
* @version date：2017年9月1日 上午11:38:53 
* 
*/
@Controller
@RequestMapping("/report")
public class ManageReportController extends BaseController {
@RequestMapping("")
String toReport(){	
	return "report";
}

@RequestMapping("/autoFill")
@ResponseBody
ResultBean autoFill(){
	String[] test={"黄田生" ,"黄哈哈"};
	ResultBean<String[]> result=new ResultBean();
	result.setData(test);
	logger.debug("自动补全被调用");
	return result;
}

}
