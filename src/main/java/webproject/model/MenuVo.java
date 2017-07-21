package webproject.model;

import java.util.ArrayList;
import java.util.List;

/** 
* @author hts
* @version date：2017年4月14日 下午11:24:39 
* 
*/
public class MenuVo {
private String menuid;
private String request;
private boolean isleaf;
private String menuname ;
private String parent=new String();
private String processname=new String();
private int level;
private String parentname;
private List<MenuVo> sonsList=new ArrayList<MenuVo>();
public int getLevel() {
	return level;
}
@Override
public String toString() {
	return "MenuVo [menuid=" + menuid + ", request=" + request + ", isleaf=" + isleaf + ", menuname=" + menuname
			+ ", parent=" + parent +  ", level=" + level + ", sonsList=" + sonsList + "]";
}
public void setLevel(int level) {
	this.level = level;
}
public List<MenuVo> getSonsList() {
	return sonsList;
}
public void setSonsList(List<MenuVo> sonsList) {
	this.sonsList = sonsList;
}
public String getMenuid() {
	return menuid;
}
public void setMenuid(String menuid) {
	this.menuid = menuid;
}
public String getRequest() {
	return request;
}
public void setRequest(String request) {
	this.request = request;
}
public boolean getIsleaf() {
	return isleaf;
}
public void setIsleaf(boolean isleaf) {
	this.isleaf = isleaf;
}
public String getMenuname() {
	return menuname;
}
public void setMenuname(String menuname) {
	this.menuname = menuname;
}
public String getParent() {
	return parent;
}
public void setParent(String parent) {
	this.parent = parent;
}

public String getProcessname() {
	return processname;
}
public void setProcessname(String processname) {
	this.processname = processname;
}
public String getParentname() {
	return parentname;
}
public void setParentname(String parentname) {
	this.parentname = parentname;
}
}
