package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.EduFileApi;
import net.edu.module.convert.ProblemCodeSampleConvert;
import net.edu.module.entity.ProblemCodeSampleEntity;
import net.edu.module.query.ProblemCodeSampleQuery;
import net.edu.module.service.ProblemCodeSampleService;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.ProblemCodeSampleVO;
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
public class ProblemCodeSampleController {
    private final ProblemCodeSampleService problemCodeSampleService;

    @Autowired
    private EduFileApi eduFileApi;


    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ProblemCodeSampleVO>> page(@Valid ProblemCodeSampleQuery query){
        PageResult<ProblemCodeSampleVO> page = problemCodeSampleService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ProblemCodeSampleVO> get(@PathVariable("id") Long id){
        ProblemCodeSampleEntity entity = problemCodeSampleService.getById(id);

        return Result.ok(ProblemCodeSampleConvert.INSTANCE.convert(entity));
    }

    @GetMapping("problem/{id}")
    @Operation(summary = "题目信息")
    public Result<CodeProblemVO> getProblem(@PathVariable("id") Long id){
        CodeProblemVO vo = problemCodeSampleService.getProblem(id);

        return Result.ok(vo);
    }

    @PostMapping("file")
    @Operation(summary = "保存样例文件")
    public Result<String> saveSample(@RequestParam("input") MultipartFile[] inFiles,@RequestParam("output") MultipartFile[] outFiles,@RequestParam("problemId") Long problemId){
        List<SampleVO> sampleVOS=eduFileApi.uploadBatch(inFiles,outFiles, problemId);
        problemCodeSampleService.saveSample(sampleVOS,problemId);
        return Result.ok();
    }


    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ProblemCodeSampleVO vo){
        problemCodeSampleService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ProblemCodeSampleVO vo){
        problemCodeSampleService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        problemCodeSampleService.delete(idList);

        return Result.ok();
    }
}