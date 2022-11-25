package net.edu.module.controller;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.ArchiveScoreBookConvert;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.service.ArchiveScoreBookService;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveScoreBookClassTableVO;
import net.edu.module.vo.ArchiveScoreBookVO;
import net.maku.entity.ArchiveScoreBookEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
* 记分册
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-22
*/
@RestController
@RequestMapping("archiveScoreBook")
@Tag(name="记分册")
@AllArgsConstructor
public class ArchiveScoreBookController {
    private final ArchiveScoreBookService archiveScoreBookService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveScoreBookVO>> page(@Valid ArchiveScoreBookQuery query){
        PageResult<ArchiveScoreBookVO> page = archiveScoreBookService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveScoreBookVO> get(@PathVariable("id") Long id){
        ArchiveScoreBookEntity entity = archiveScoreBookService.getById(id);

        return Result.ok(ArchiveScoreBookConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveScoreBookVO vo){
        archiveScoreBookService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveScoreBookVO vo){
        archiveScoreBookService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveScoreBookService.delete(idList);

        return Result.ok();
    }

    @PostMapping("InsertClassInfo")
    @Operation(summary = "生成课程信息")
    public Result<String> InsertClassInfo(@RequestBody ArchiveScoreBookClassInfoVO vo){

        archiveScoreBookService.InsertClassInfo(vo);

        return Result.ok(String.valueOf(vo.getId()));
    }



    @GetMapping("getClassTable")
    @Operation(summary = "课程表")
    public Result<List<ArchiveScoreBookClassTableVO>> getClassTable(@RequestParam("id")String id){
        List<ArchiveScoreBookClassTableVO> archiveScoreBookClassTableVOList=archiveScoreBookService.getClassTable(id);
        return Result.ok(archiveScoreBookClassTableVOList);
    }

    @GetMapping("deleteClassTable")
    @Operation(summary = "删除课程表")
    public Result<List<ArchiveScoreBookClassTableVO>> deleteClassTable(@RequestParam("id")String id,@RequestParam("deleteId")String deleteId){
        archiveScoreBookService.deleteClassTable(id,deleteId);
        return Result.ok();
    }


    @GetMapping("updateClassTable")
    @Operation(summary = "添加课程表")
    public Result<String> addClassTable(@RequestParam("id")String id,@RequestParam("classSchedule")String classSchedule){
        archiveScoreBookService.updateClassTable(id,classSchedule);
        return Result.ok();
    }



}
