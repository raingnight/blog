package top.fx7.yinlu.VO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class R<T> {
    private Integer state;
    private String message;
    private T data;

    public static R ok(){
        return new R().setState(State.OK);
    }
    public static <T> R ok(T data){
        return new R().setData(data).setState(2000);
    }
    public static R fail(Integer state,Throwable e){
        return new R().setState(state).setMessage(e.getMessage());
    }
    public static interface State{
        int OK = 2000;
        int ERR_INSERT = 4001;
        int ERR_USER_EXISTED = 4003;
        int ERR_UPDATE = 4004;
        int ERR_PERMISSION_NOT_ALLOWED = 4005;
        int ERR_UPLOAD_IMAGE = 4006;
        int ERR_UNKNOWN = 9999;
    }
}
