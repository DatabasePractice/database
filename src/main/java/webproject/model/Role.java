package webproject.model;
/** 
* @author hts
* @version date：2017年4月14日 下午3:56:44 
* 
*/
public class Role {
	/**
	 * 
	 * @param roleid
	 * @param rolename
	 */
public Role(String roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
public Role(){}
private String roleid;
private String rolename;
private String menus;
public String getRoleid() {
	return roleid;
}
public void setRoleid(String roleid) {
	this.roleid = roleid;
}
public String getRolename() {
	return rolename;
}
public void setRolename(String rolename) {
	this.rolename = rolename;
}
public String getMenus() {
	return menus;
}
public void setMenus(String menus) {
	this.menus = menus;
}

}
