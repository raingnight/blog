package top.fx7.yinlu.service.ex;

public class PermissionNotAllowed extends ServiceException{
    public PermissionNotAllowed() {
    }

    public PermissionNotAllowed(String message) {
        super(message);
    }

    public PermissionNotAllowed(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionNotAllowed(Throwable cause) {
        super(cause);
    }

    public PermissionNotAllowed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
