package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduFileApi;
import net.edu.module.convert.CodeSampleConvert;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.service.CodeSampleService;
import net.edu.module.vo.CodeSampleVO;
import net.edu.module.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@RestController
@RequestMapping("sample")
@Tag(name="测试样例表")
@AllArgsConstructor
public class CodeSampleController {
    private final CodeSampleService codeSampleService;

    @Autowired
    private EduFileApi eduFileApi;


    @GetMapping("list/{problemId}")
    @Operation(summary = "获取题目测试样例的信息")
    public Result<List<CodeSampleVO>> getList(@PathVariable("problemId") Long  problemId){
        List<CodeSampleVO> list = codeSampleService.getList(problemId);
        return Result.ok(list);
    }

    @PostMapping("file")
    @Operation(summary = "保存样例文件")
    public Result<String> saveSample(@RequestParam("input") MultipartFile[] inFiles,@RequestParam("output") MultipartFile[] outFiles,@RequestParam("problemId") Long problemId){
        List<SampleVO> sampleVos=eduFileApi.uploadBatch(inFiles,outFiles, problemId);
        codeSampleService.saveSample(sampleVos,problemId);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        codeSampleService.delete(idList);

        return Result.ok();
    }
}