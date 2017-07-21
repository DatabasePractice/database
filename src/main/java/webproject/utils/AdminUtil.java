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

	public static boolean isSuperById(String id) {
		if (id.equals(superId)) {
			return true;
		} else
			return false;
	}
	
}
