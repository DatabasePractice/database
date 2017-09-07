package webproject.model;
/** 
* @author hts
* @version date：2017年9月4日 下午10:53:32 
* 返回的结果类
* Controller ResponseBody返回的内容必须是该类，统一标准，方便前后端协调开发
*/
public class ResultEntity<T> {
private Integer status;

//    error_msg 错误信息，若status为0时，为success
private String msg;

//    content 返回体报文的出参，使用泛型兼容不同的类型
private T data;

public Integer getStatus() {
    return status;
}

public void setStatus(Integer code) {
    this.status = code;
}

public String getMsg() {
    return msg;
}

public void setMsg(String msg) {
    this.msg = msg;
}

public T getData(Object object) {
    return data;
}

public void setData(T data) {
    this.data = data;
}

public T getData() {
    return data;
}

@Override
public String toString() {
    return "Result{" +
            "status=" + status +
            ", msg='" + msg + '\'' +
            ", data=" + data +
            '}';
}
}
