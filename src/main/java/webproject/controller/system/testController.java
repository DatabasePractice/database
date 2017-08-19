package webproject.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webproject.controller.base.BaseController;

/** 
* @author hts
* @version date：2017年8月16日 下午1:58:31 
* 
*/
@Controller
public class testController extends BaseController {
	@RequestMapping("/haha/hi")
	public String register(Model model ,HttpServletRequest request) {
		String p=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		logger.debug("context路径"+p);
		return "register";
	}

}
