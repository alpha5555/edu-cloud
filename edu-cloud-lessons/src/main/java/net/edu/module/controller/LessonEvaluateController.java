package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.LessonEvaluateConvert;
import net.edu.module.entity.LessonEvaluateEntity;
import net.edu.module.query.EvaluateGenerateQuery;
import net.edu.module.query.LessonEvaluateQuery;
import net.edu.module.service.LessonEvaluateService;
import net.edu.module.vo.LessonEvaluateVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 课堂评价
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-18
*/
@RestController
@RequestMapping("evaluate")
@Tag(name="课堂评价")
@AllArgsConstructor
public class LessonEvaluateController {
    private final LessonEvaluateService lessonEvaluateService;


    @GetMapping("list/{lessonId}")
    @Operation(summary = "列表")
    public Result<List<LessonEvaluateVO>> list(@PathVariable("lessonId") Long lessonId){
        return Result.ok(lessonEvaluateService.list(lessonId));
    }

    @PostMapping
    @Operation(summary = "生成")
    public Result<String> generate(@RequestBody EvaluateGenerateQuery query){
        System.out.println("Id:" + query.getLessonId());
        lessonEvaluateService.generate(query.getLessonId() , query.getExcellent() , query.getMedium() , query.getFail());
        return Result.ok();
    }

    @PostMapping ("updateEvaluation")
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody LessonEvaluateVO vo){
        lessonEvaluateService.update(vo);
        return Result.ok();
    }

    @GetMapping("send/{lessonId}")
    @Operation(summary = "列表")
    public Result<String> sendEvaluate(@PathVariable("lessonId") Long lessonId){
        lessonEvaluateService.sendEvaluate(lessonId);
        return Result.ok();
    }
}