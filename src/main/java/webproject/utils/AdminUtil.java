package webproject.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 *  
 * 
 * @author hts
 * @version date：2017年7月14日 下午2:55:07 
 * 
 */
public class AdminUtil {
	@Value("${privilege.superId}")
	public static String superId;
	@Value("${privilege.superRoleId}")
	public static String superRoleId;
	public static boolean isSuperById(String id) {
		if (id.equals(superId)) {
			return true;
		} else
			return false;
	}
	
	public static boolean isSuperByRoleId(String id) {
		if (id.equals(superRoleId)) {
			return true;
		} else
			return false;
	}
}
