package net.edu.module.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.EnrollClassConvert;
import net.edu.module.entity.*;
import net.edu.module.query.EnrollLessonQuery;
import net.edu.module.service.EnrollLessonService;
import net.edu.module.service.EnrollUserService;
import net.edu.module.vo.EnrollClassVO;
import net.edu.module.vo.EnrollLessonVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("enrollLesson")
@Tag(name="试听课程")
@AllArgsConstructor
public class EnrollLessonController {

    private final EnrollLessonService enrollLessonService;
    private final EnrollUserService enrollUserService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollLessonVO>> page(@Valid EnrollLessonQuery query){
        System.out.println(query);
        PageResult<EnrollLessonVO> page = enrollLessonService.page(query);
        System.out.println(page);
        return Result.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> saveLesson(@RequestBody EnrollLessonVO vo){
        System.out.println(vo);
        enrollLessonService.saveLesson(vo);
        return Result.ok();
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Object get(@PathVariable("id") Long id){
        EnrollLessonEntity entity = enrollLessonService.getLessonById(id);
        return Result.ok(entity);
    }

    @PutMapping
    @Operation(summary = "修改")
    public Object update(@RequestBody @Valid EnrollLessonVO vo){
        enrollLessonService.updateLesson(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        enrollLessonService.delete(idList);

        return Result.ok();
    }

    @GetMapping("SelectOne")
    @Operation(summary = "下拉框选择任课老师")
    public Object getSelectOne() {
        List<EnrollSelectOne> entity = enrollLessonService.getSelectOne();
        return Result.ok(entity);
    }

    @PostMapping("joinLesson")
    @Operation(summary = "加入试听课")
    public Object joinLesson(@RequestBody EnrollJoinLessonEntity entity) {
        enrollLessonService.joinLesson(entity);
        return Result.ok();
    }

    @PostMapping("teacherOpinion")
    @Operation(summary = "教师评价")
    public Object updateOpinion(@RequestBody EnrollOpinionEntity entity) {
        System.out.println(entity);
        if(entity.getTeacherOpinion() == null && entity.getParentOpinion() == null) {
            return Result.error();
        }
        enrollLessonService.updateOpinion(entity);
        return Result.ok();
    }


}
