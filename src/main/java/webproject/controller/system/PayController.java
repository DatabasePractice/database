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
import webproject.service.AccountService;
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
@RequestMapping("pay")
public class PayController extends BaseController {
	@Autowired
	CustomService customService;
	@Autowired
	AccountService accountService;
	@RequestMapping("")
	public String toPaymentTable(Model model) {
		return "pay";
	}

	@RequestMapping("/SearchCustom")
	@ResponseBody
	public ResultBean SearchCustom() {
		PageData pd = this.getPageData();
		PageData returnpd=customService.queryCustom(pd);
		if(returnpd==null||returnpd.isEmpty())
		  return this.responseMsg("找不到该用户", 500,null);
		else	return this.responseMsg(null, 200,returnpd);
		
	}
	
	@RequestMapping("/startPay")
	@ResponseBody
	public ResultBean startPay() throws Exception {
		PageData pd = this.getPageData();
		String TEL=pd.getString("TEL");
		String cost=pd.getString("cost");
		pd.put("account", this.getUsername());
		accountService.Updateamount(pd);
		return this.responseMsg("缴费成功,已为电话号码【"+TEL+"】充值"+cost+"元", 200,null);
		
	}
}
