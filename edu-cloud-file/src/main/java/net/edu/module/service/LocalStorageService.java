package net.edu.module.service;

import net.edu.framework.common.exception.ServerException;
import net.edu.module.properties.LocalStorageProperties;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 */
public class LocalStorageService extends StorageService {

   LocalStorageProperties properties=new LocalStorageProperties();

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }



    @Override
    public String upload(InputStream inputStream, String path) {

        try {
            File file = new File(properties.getPath() + File.separator + path);

            // 没有目录，则自动创建目录
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("目录 '" + parent + "' 创建失败");
            }

            FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
        } catch (Exception e) {
            throw new ServerException("上传文件失败：", e);
        }

        return properties.getDomain() + "/" + properties.getUrl() + "/" + path;
    }
}