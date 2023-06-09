package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveCourseConvert;
import net.edu.module.entity.ArchiveCourseEntity;
import net.edu.module.service.ArchiveCourseService;
import net.edu.module.query.ArchiveCourseQuery;
import net.edu.module.vo.ArchiveCourseVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 能力课程
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@RestController
@RequestMapping("course")
@Tag(name="能力课程")
@AllArgsConstructor
public class ArchiveCourseController {
    private final ArchiveCourseService archiveCourseService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveCourseVO>> page(@Valid ArchiveCourseQuery query){
        PageResult<ArchiveCourseVO> page = archiveCourseService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveCourseVO> get(@PathVariable("id") Long id){
        ArchiveCourseEntity entity = archiveCourseService.getById(id);
        System.out.println("123"+entity);
        return Result.ok(ArchiveCourseConvert.INSTANCE.convert(entity));
    }


    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveCourseVO vo){
        archiveCourseService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveCourseVO vo){
        System.out.println(vo);
        archiveCourseService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveCourseService.delete(idList);

        return Result.ok();
    }

    @GetMapping("course")
    @Operation(summary = "课程信息")
    public Result<List<ArchiveCourseVO>> selectArchiveCourseAll(){
        return Result.ok(archiveCourseService.selectArchiveCourseAll());
    }

    @GetMapping("course1")
    @Operation(summary = "课程信息")
    public Result<List<ArchiveCourseVO>> selectArchiveCourse(String grade){
        return Result.ok(archiveCourseService.selectArchiveCourse(grade));
    }
}
