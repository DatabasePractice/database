package webproject.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webproject.mapper.RoleMapper;
import webproject.mapper.UserMapper;
import webproject.model.PageData;
import webproject.model.ResultBean;
import webproject.model.system.MenuVo;
import webproject.model.system.Role;
import webproject.model.system.User;
import webproject.service.AuthorizeService;
import webproject.service.UserService;
import webproject.utils.AdminUtil;

/**
 *  
 * 
 * @author hts
 * @version date：2017年8月19日 下午9:09:07 
 * 
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	RoleMapper rolemapper;
	@Autowired
	UserMapper usermapper;

	/* (non-Javadoc)
	 * @see webproject.service.impl.UserService#findmenus(webproject.model.system.User)
	 */
	@Override
	public List<MenuVo> findmenus(User user) {
		return usermapper.findmenus(user);
	}

	/* (non-Javadoc)
	 * @see webproject.service.impl.UserService#findRole(webproject.model.system.User)
	 */
	@Override
	public Role findRole(User user) {
		return usermapper.findRole(user);
	}
	
	/* (non-Javadoc)
	 * @see webproject.service.impl.UserService#findUser(webproject.model.system.User)
	 */
	@Override
	public List <User> findUser(User user) {
		return usermapper.findUser(user);
	}
	
	/* (non-Javadoc)
	 * @see webproject.service.impl.UserService#doLogin(webproject.model.system.User)
	 */
	@Override
	public ResultBean doLogin(User user) {
		ResultBean result=new ResultBean();
		if (usermapper.findsameaccount(user) == 0) {
			usermapper.insertUser(user);
			UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
			SecurityUtils.getSubject().login(token);
			result.setStatus(200);
			result.setMsg("登陆成功");
		} else {
			result.setStatus(500);
			result.setMsg("账号已被注册");
		}
		
		
		return result;
	}
	
	@Override
	public int findusercount() {
		 return usermapper.findusercount();
		}
	@Override
	public List findUserAndRole(User user){
		return usermapper.findUserAndRole(user);
}
	
	@Override
	public ResultBean updateOrInsert(User user, int updatemode) throws Exception{
		ResultBean result = new ResultBean();
		if (AdminUtil.isSuperById(user.getId())) {
			result.setMsg ("超级管理员不能修改");
			return result;
		}

		// 更新操作
		if (updatemode == 0) {
			usermapper.updateUser(user);
			result.setMsg ("修改成功");
		}
		// 增加操作
		if (updatemode == 1) {		
			usermapper.insertUser(user);
			result.setMsg ("新增成功");
		}
        return result;
	}
	
	@Override
	public ResultBean BatchDeleteUser(List<String> ids) throws Exception{
		ResultBean result = new ResultBean();
		result.setMsg("删除成功");
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			if (AdminUtil.isSuperById(id)) {
				result.setMsg("超级管理员不能删除");
				continue;
			}
			usermapper.deleteUser(id);
		}
        return result;
	}

	@Override
	public ResultBean updatePassword(String username, String password,String oldpassword) throws Exception {
		// TODO Auto-generated method stub
		ResultBean result= new ResultBean();
		User user=new User(null,username,password,null,null);
		User olduser=usermapper.findUser(user).get(0);
		if(!olduser.getPassword().equals(oldpassword)){
		result.setMsg("旧密码不正确，修改失败");
		result.setStatus(500);
	    return result;
		}
		usermapper.updatePasswordByAccount(user);
		result.setMsg("修改成功");
		return result;
	}
	
	@Override
	public  Integer findUserIDByAccount(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		String account=pd.getString("account");
		if(account!=null){
		User user=new User();
		user.setAccount(account);
		List<User> userlist= usermapper.findUser(user);
		if((user=userlist.get(0))!=null){
         String useridstr=user.getId();
         int userid=Integer.parseInt(useridstr);
		  return  userid;
		}
		else return null;
		}
		else throw  new RuntimeException("account不能为空");	
	}
}
