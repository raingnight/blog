package top.fx7.yinlu.controller;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.fx7.yinlu.VO.R;
import top.fx7.yinlu.service.ex.UploadException;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping("")
    public R<Void> get(@RequestParam("imageFile") MultipartFile multipartFile) {
        //AK 从https://portal.qiniu.com/user/key获取
        String accessKey = "57THhc6zpcwkT4I4tdwfX8OlC2IXvU34pcc4yXLt";
        //SK 和AK在同一地方获取
        String secretKey = "uiUdVCl64yiAEM6BxhfZliyR0s-PN_RcoprnXzI8";
        //bucket 自己创建的存储空间名
        String bucket = "rjgl";
        //Configuration 构造方法中的参数是指定上传到哪里的服务器，可以不指定即无参，我指定的是华南地区服务器
        Configuration cf = new Configuration(Region.huanan());
        UploadManager um = new UploadManager(cf);
        //创建上传token
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket);
        Response response = null;
        try {
            // 上传 并返回结果
            // put 方法的三个参数：
            //                 要上传的数据的字节数组       上传存储的文件名（我的是按文件原名存储）    token
            response = um.put(multipartFile.getBytes(), multipartFile.getOriginalFilename(), token);
        } catch (QiniuException e) {
            throw new UploadException("服务器错误!请稍后再试！");
        } catch (IOException e) {
            throw new UploadException("服务器错误!请稍后再试！");
        }
        //上传结果状态码为200表示上传成功 否则失败
        if (response.statusCode==200){
            return R.ok();
        }else {
            return R.fail(R.State.ERR_UNKNOWN,new Throwable("上传失败！未知错误！"));
        }
    }
}
