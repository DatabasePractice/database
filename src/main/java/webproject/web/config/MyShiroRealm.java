package webproject.web.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import webproject.mapper.UserMapper;
import webproject.model.Role;
import webproject.model.User;
import webproject.service.libraryService;

/**
 *  
 * 
 * @author hts
 * @version date：2017年4月12日 下午11:12:56 
 * 
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {
	@Autowired
	libraryService myservice;
	@Autowired
	UserMapper mapper;
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String username = (String) super.getAvailablePrincipal(principals);
		User user = new User();
		user.setAccount(username);
		Role role = mapper.findRole(user);
		logger.info("+++++++授权获取+++++++++++"+role.getRolename());
		if (role != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			Set<String> roleSet = new HashSet<String>();
			roleSet.add(role.getRolename());
			info.setRoles(roleSet);
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = new User();
		user.setAccount(username);
		List<User> list = myservice.findUser(user);
		if(list!=null&&!list.isEmpty())
			{User tempuser = list.get(0);
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			return new SimpleAuthenticationInfo(tempuser.getAccount(), tempuser.getPassword(), getName());
		}

		return null;
	}

}
