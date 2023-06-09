package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.ArchiveExamQuery;
import net.edu.module.service.ArchiveExamService;
import net.edu.module.vo.ArchiveExamVO;
import net.edu.module.vo.ClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author weng
 * @date 2022/11/10 - 16:26
 **/
@RestController
@RequestMapping("exam")
@Tag(name="考试")
@AllArgsConstructor
public class ArchiveExamController {

    @Autowired
    private ArchiveExamService archiveExamService;

    @GetMapping("/insertExam")
    @Operation(summary = "考试数据迁移")
    public Result<Integer> insertExam(){
        System.out.println("考试数据迁移");
        return Result.ok(archiveExamService.insertExam());
    }

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveExamVO>> page(@Valid ArchiveExamQuery query){
        PageResult<ArchiveExamVO> page = archiveExamService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveExamVO> get(@PathVariable("id") Long id){
        return Result.ok(archiveExamService.selectExamById(id));
    }

    @GetMapping("course/{courseId}/{classId}")
    @Operation(summary = "信息")
    public Result<List<ArchiveExamVO>> selectExamByCourseId(@PathVariable("courseId") Long courseId, @PathVariable("classId") Long classId){
        return Result.ok(archiveExamService.selectExamByCourseId(courseId,classId));
    }

    @GetMapping("course/{courseId}")
    @Operation(summary = "信息")
    public Result<List<ClassVO>> selectExamByCourseId(@PathVariable("courseId") Long courseId){
        return Result.ok(archiveExamService.selectClassByCourseId(courseId));
    }
}
