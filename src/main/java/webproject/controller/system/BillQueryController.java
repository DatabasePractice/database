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
import webproject.service.BillQueryService;
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
@RequestMapping("billquery")
public class BillQueryController extends BaseController {
	@Autowired
	BillQueryService billqueryService;

	@RequestMapping("")
	public String toPaymentTable(Model model) {

		return "billquery";
	}


}
