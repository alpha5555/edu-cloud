package net.edu.module.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import net.edu.framework.common.utils.Result;

import net.edu.module.fallback.EduFileApiFallBack;
import net.edu.module.vo.FileUploadVO;
import net.edu.module.vo.SampleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@FeignClient(value = "edu-cloud-file", fallbackFactory = EduFileApiFallBack.class)
public interface EduFileApi {


    @PostMapping(value ="upload2",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    @Operation(summary = "不对外公开文件")
    Result<FileUploadVO> upload2(@RequestPart("file") MultipartFile file);

    /****************************Judge调用******************************************/
    @GetMapping("/sample/fileBase64")
    @Operation(summary = "测试样例转Base64")
    Result<String> getFileContent(@RequestParam("path") String path);


    /****************************Problem调用******************************************/
    @PostMapping(value = "/sample/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    FileUploadVO upload(@RequestPart("file") MultipartFile file, @RequestParam("problemId")String problemId);


    @PostMapping(value ="/sample/upload/batch",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    List<SampleVO> uploadBatch(@RequestPart("input") MultipartFile[] input, @RequestPart("output") MultipartFile[] output, @RequestParam("problemId") Long problemId) ;


    @GetMapping("/sample/getFileList")
    Result<List<File>> getFileList(@RequestParam("pathList") List<String> pathList);



}
