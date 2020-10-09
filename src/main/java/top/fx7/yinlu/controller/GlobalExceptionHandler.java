package top.fx7.yinlu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fx7.yinlu.VO.R;
import top.fx7.yinlu.service.ex.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public R handleException(Throwable e) {
        if (e instanceof InsertException) {
            return R.fail(R.State.ERR_INSERT, e);
        } else if (e instanceof UserExistedException) {
            return R.fail(R.State.ERR_USER_EXISTED, e);
        } else if (e instanceof UpdateException) {
            return R.fail(R.State.ERR_UPDATE, e);
        }else if (e instanceof PermissionNotAllowed) {
            return R.fail(R.State.ERR_PERMISSION_NOT_ALLOWED, e);
        }else if (e instanceof UploadException) {
            return R.fail(R.State.ERR_UPLOAD_IMAGE, e);
        } else {
            log.debug("未知的错误 >>> {}", e);
            return R.fail(R.State.ERR_UNKNOWN, e);
        }
    }
}
