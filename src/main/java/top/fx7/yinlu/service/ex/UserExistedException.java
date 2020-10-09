package top.fx7.yinlu.service.ex;

public class UserExistedException extends ServiceException{
    public UserExistedException() {
    }

    public UserExistedException(String message) {
        super(message);
    }

    public UserExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistedException(Throwable cause) {
        super(cause);
    }

    public UserExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
