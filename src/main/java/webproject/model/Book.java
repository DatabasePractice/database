package webproject.model;
/** 
* @author hts
* @version date：2017年6月25日 下午9:29:11 
* 
*/
public class Book {
public Book(String bnum, String bname, String bauthor, int havelend) {
		super();
		this.bnum = bnum;
		this.bname = bname;
		this.bauthor = bauthor;
		this.havelend = havelend;
	}
private String bnum;
private String bname;
private String bauthor;
private int havelend;
public String getBnum() {
	return bnum;
}
public void setBnum(String bnum) {
	this.bnum = bnum;
}
public String getBname() {
	return bname;
}
public void setBname(String bname) {
	this.bname = bname;
}
public String getBauthor() {
	return bauthor;
}
public void setBauthor(String bauthor) {
	this.bauthor = bauthor;
}
public int getHavelend() {
	return havelend;
}
public void setHavelend(int havelend) {
	this.havelend = havelend;
}
	
}
