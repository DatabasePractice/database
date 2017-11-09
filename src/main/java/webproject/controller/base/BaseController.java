package webproject.controller.base;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.utils.UuidUtil;

/**
 * @author hts
 * 修改时间：2017、8、11
 */
public class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;
	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	public static ResultBean responseMsg(String msg,int status,Object t){
		ResultBean result = new ResultBean();
		result.setData(t);
		result.setMsg(msg);
		result.setStatus(status);
		return result;
	}
	public static String getUsername(){
		if(SecurityUtils.getSubject()!=null)
		return  SecurityUtils.getSubject().getPrincipal().toString();
		else return null;
	}
	
}
