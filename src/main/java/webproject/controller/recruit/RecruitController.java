package webproject.controller.recruit;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webproject.controller.base.BaseController;
import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.service.RecruitService;

/**
 * 
* @author hts
* @version date：2017年11月10日 下午5:19:23 
*
 */
@Controller
@RequestMapping(value="/recruit")
public class RecruitController extends BaseController {
	
	@Autowired
	private RecruitService recruitService;
	
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultBean delete() throws Exception{
		ResultBean resultbean=this.responseMsg("删除成功", 200, null);
		PageData pd=this.getPageData();
		recruitService.delete(pd);
		return resultbean;
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ResultBean edit() throws Exception{
		ResultBean resultbean=this.responseMsg("更新成功", 200, null);
		PageData pd=this.getPageData();
		recruitService.edit(pd);
		return resultbean;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public PageData list() throws Exception{
		PageData pd=this.getPageData();
		return recruitService.list(pd);
	}
	
	/**excel导入
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excelImport")
	@ResponseBody
	public ResultBean excelImport(@RequestParam(value="excel",required=false) MultipartFile file)throws Exception{
		ResultBean resultbean=this.responseMsg("导入成功", 200, null);
		FileInputStream fi=(FileInputStream)file.getInputStream();
		PageData pd = new PageData();
		recruitService.importByExcel(fi);
		return resultbean;
	}	
	
	
	
	
//	 /**导出到excel
//	 * @param
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/excel")
//	public ModelAndView exportExcel() throws Exception{
//		logBefore(logger, Jurisdiction.getUsername()+"导出Fsa到excel");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
//		ModelAndView mv = new ModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		Map<String,Object> dataMap = new HashMap<String,Object>();
//		List<String> titles = new ArrayList<String>();
//		titles.add("备注1");	//1
//		titles.add("备注2");	//2
//		titles.add("备注3");	//3
//		titles.add("备注4");	//4
//		titles.add("备注5");	//5
//		titles.add("备注6");	//6
//		titles.add("备注7");	//7
//		titles.add("备注8");	//8
//		titles.add("备注9");	//9
//		titles.add("备注10");	//10
//		titles.add("备注11");	//11
//		titles.add("备注12");	//12
//		titles.add("备注13");	//13
//		titles.add("备注14");	//14
//		titles.add("备注15");	//15
//		titles.add("备注16");	//16
//		titles.add("备注17");	//17
//		titles.add("备注18");	//18
//		titles.add("备注19");	//19
//		dataMap.put("titles", titles);
//		List<PageData> varOList = fsaService.listAll(pd);
//		List<PageData> varList = new ArrayList<PageData>();
//		for(int i=0;i<varOList.size();i++){
//			PageData vpd = new PageData();
//			vpd.put("var1", varOList.get(i).get("RECRUIT_ID").toString());	//1
//			vpd.put("var2", varOList.get(i).getString("TIME"));	    //2
//			vpd.put("var3", varOList.get(i).getString("NAME"));	    //3
//			vpd.put("var4", varOList.get(i).getString("SEX"));	    //4
//			vpd.put("var5", varOList.get(i).get("AGE").toString());	//5
//			vpd.put("var6", varOList.get(i).getString("JG"));	    //6
//			vpd.put("var7", varOList.get(i).getString("YPBM"));	    //7
//			vpd.put("var8", varOList.get(i).getString("YPGW"));	    //8
//			vpd.put("var9", varOList.get(i).getString("ZPQD"));	    //9
//			vpd.put("var10", varOList.get(i).getString("LXDH"));	    //10
//			vpd.put("var11", varOList.get(i).getString("YX"));	    //11
//			vpd.put("var12", varOList.get(i).getString("XL"));	    //12
//			vpd.put("var13", varOList.get(i).getString("BYXX"));	    //13
//			vpd.put("var14", varOList.get(i).getString("ZY"));	    //14
//			vpd.put("var15", varOList.get(i).getString("LXSJ"));	    //15
//			vpd.put("var16", varOList.get(i).getString("MSSJ"));	    //16
//			vpd.put("var17", varOList.get(i).getString("MSJG"));	    //17
//			vpd.put("var18", varOList.get(i).getString("BZ"));	    //18
//			vpd.put("var19", varOList.get(i).getString("ZPFZR"));	    //19
//			varList.add(vpd);
//		}
//		dataMap.put("varList", varList);
//		ObjectExcelView erv = new ObjectExcelView();
//		mv = new ModelAndView(erv,dataMap);
//		return mv;
//	}
}
