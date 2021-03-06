package webproject.controller.system;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import webproject.controller.base.BaseController;
import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.model.system.Role;
import webproject.model.system.User;
import webproject.service.AuthorizeService;
import webproject.service.CustomService;
import webproject.service.PaymentService;
import webproject.service.UserService;

/**
 *  
 * 
 * @author hts
 * @version date：2017年9月10日 下午6:33:01 
 * 
 */
@Controller
@RequestMapping("charge")
public class ChargeController extends BaseController {
	@Autowired
	CustomService customService;

	@RequestMapping("")
	public String toPaymentTable(Model model) {

		return "payment";
	}

	@RequestMapping("/queryCutomByTEL")
	@ResponseBody
	public ResultBean queryCutomByTEL() {
		PageData pd = this.getPageData();
		
		PageData returnPd= customService.queryCustom(pd);
		if(returnPd==null||returnPd.isEmpty())
			return responseMsg("找不到该用户", 500, null);			
		else return responseMsg("查找成功", 200, returnPd);
	
}
}