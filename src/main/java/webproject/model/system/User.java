package webproject.model.system;
/** 
* @author hts
* @version date：2017年4月11日 下午7:07:24 
* @param name
* @param account
* @param password
* @param roleid
* @param id
*/
public class User {
	/**
	 * 
	 * @param name
	 * @param account
	 * @param password
	 * @param roleid
	 * @param id
	 */
public User(String name, String account, String password, String roleid, String id) {
		super();
		this.name = name;
		this.account = account;
		this.password = password;
		this.roleid = roleid;
		this.id = id;
	}
public User() {
	super();
}
private String name;
private String account;
private String password;
private String roleid;
private String id;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRoleid() {
	return roleid;
}
public void setRoleid(String roleid) {
	this.roleid = roleid;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}
