package net.edu.module.controller;

import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.utils.QrCodeUtil;
import net.edu.framework.common.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author weng
 * @date 2023/4/7 - 11:36
 **/
@RestController
@RequestMapping("/qr-code")
@Slf4j
public class QrCodeController {

    @Value("${wechat.classUrl}")
    private String url;

    /**
     * 响应文件流
     *
     * @param response response原生响应文件流
     */
    @GetMapping("getPromotionQR")
    public void getPromotionQR(HttpServletResponse response, @RequestParam("id")Long id) throws IOException {
        byte[] qrCode = null;
        //拼接url，传参
        String newUrl = url+"?id=" + id;
        try {
            qrCode = QrCodeUtil.generateQrCodeByte(newUrl, 350, 350);
        } catch (Exception e) {
            log.info("Exception:{}", e.getMessage());
        }
        // Header设置文件类型（ContentType不设置也没事）
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.getOutputStream().write(Objects.requireNonNull(qrCode));
    }

    /**
     * 下载到本地
     *
     */
    @GetMapping("downloadQR")
    public Result<String> downloadQR(@RequestParam("id")Long id,@RequestParam("name")String name) throws IOException, WriterException {
        //下载路径及文件名
        String filePath = "D:\\"+name+"邀请码.png";
        String newUrl = url+"?id=" + id;
        QrCodeUtil.generateQrCodeImage(newUrl, 350, 350, filePath);
        return Result.ok("下载成功！请前往D盘查看");
    }

}