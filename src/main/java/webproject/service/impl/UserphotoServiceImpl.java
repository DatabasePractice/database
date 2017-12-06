package webproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webproject.mapper.UserphotoMapper;
import webproject.model.PageData;
import webproject.service.UserphotoService;
import webproject.utils.DelAllFile;
import webproject.utils.PathUtil;
import webproject.utils.Tools;

/**
 *  
 * 
 * @author hts
 * @version date：2017年12月6日 上午12:05:33 
 * 
 */
@Service
public class UserphotoServiceImpl implements UserphotoService {

	@Autowired
	private UserphotoMapper userphotoMapper;

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception {
		userphotoMapper.save(pd);
	}

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception {
		userphotoMapper.delete(pd);
	}

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd) throws Exception {
		userphotoMapper.edit(pd);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return userphotoMapper.findById(pd);
	}

	@Override
	public void savephoto(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		
		PageData ypd = this.findById(pd);
		if (null == ypd) { // 没有数据就新增，否则就修改
			this.save(pd);
		} else {
			this.edit(pd);
			String PHOTO0 = ypd.getString("PHOTO0");
			String PHOTO1 = ypd.getString("PHOTO1");
			if (Tools.notEmpty(PHOTO0)) {
				DelAllFile.delFolder(PathUtil.getClassResources() +"public/"+PHOTO0); // 删除原图
			}
			if (Tools.notEmpty(PHOTO1)) {
				DelAllFile.delFolder(PathUtil.getClassResources() +"public/"+ PHOTO1);
			} // 删除图1

		}
	}

}
