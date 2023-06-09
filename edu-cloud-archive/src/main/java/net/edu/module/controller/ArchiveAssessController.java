package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveAssessService;
import net.edu.module.query.ArchiveAssessQuery;
import net.edu.module.vo.*;
import net.edu.module.vo.ArchiveAssessByCourseIdVo;
import net.edu.module.vo.ArchiveAssessVO;
import net.edu.module.vo.ArchivePointAndTargetVO;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
* 考核点
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-26
*/
@RestController
@RequestMapping("assess")
@Tag(name="考核点")
@AllArgsConstructor
public class ArchiveAssessController {
    private final ArchiveAssessService archiveAssessService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<ArchiveAssessVO>> page(@Valid ArchiveAssessQuery query){
        PageResult<ArchiveAssessVO> page = archiveAssessService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<ArchiveAssessVO> get(@PathVariable("id") Long id){
        return Result.ok(archiveAssessService.selectArchiveAssessById(id));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody ArchiveWeightTargetAssessVO vo){
        archiveAssessService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid ArchiveAssessVO vo){
        archiveAssessService.update(vo);
        return Result.ok();
    }

    @PostMapping("addAssess")
    @Operation(summary = "保存")
    public Result<String> save1(@RequestBody ArchiveAssessVO vo){
        archiveAssessService.save1(vo);
        return Result.ok();
    }

    @PutMapping("addAssess")
    @Operation(summary = "修改")
    public Result<String> update1(@RequestBody ArchiveAssessVO vo){
        archiveAssessService.update1(vo);
        return Result.ok();
    }

    @GetMapping("deleteById")
    @Operation(summary = "删除考核点")
    public Result<String> deleteAssess(@RequestParam("id")Long id){
        archiveAssessService.deleteAssess(id);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        archiveAssessService.delete(idList);
        return Result.ok();
    }

    @GetMapping("course")
    @Operation(summary = "根据课程id获取权重")
    public Result<List<ArchiveAssessVO>> selectAssessByCourseId(Long courseId){
        return Result.ok(archiveAssessService.selectAssessByCourseId(courseId));
    }

    @PostMapping("/import")
    public Result<String> assessFromExcel(@RequestParam("file") MultipartFile file) {
        archiveAssessService.assessFromExcel(file);
        return Result.ok();
    }


    @GetMapping("stepTwo")
    @Operation(summary = "获取考核比例")
    public Result<ArchiveAssessVO> getSummaryStep2(@RequestParam String courseId) {
        ArchiveAssessVO list = archiveAssessService.getSummaryStep2(courseId);
        return Result.ok();
    }

    @GetMapping("/byCourseId")
    @Operation(summary = "通过courseId查找考核点")
    public Result<List<ArchiveAssessByCourseIdVo>> getAssessByCourseId(@RequestParam String courseId) {
        List<ArchiveAssessByCourseIdVo> list = archiveAssessService.getAssessByCourseId(courseId);
        return Result.ok(list);
    }

    @GetMapping("/byTargetId")
    @Operation(summary = "通过targetId查找考核点")
    public Result<List<ArchiveAssessByCourseIdVo>> getAssessByTargetId(@RequestParam String targetId) {
        List<ArchiveAssessByCourseIdVo> list = archiveAssessService.getAssessByTargetId(targetId);
        System.out.println(targetId);
        return Result.ok(list);
    }

    @GetMapping("/deleteByCourseId")
    @Operation(summary = "通过courseId删除")
    public Result<String> deleteByCourseId(@RequestParam String courseId , String assessId) {
        archiveAssessService.deleteByCourseId(courseId , assessId);
        return Result.ok();
    }


    @PostMapping("/saveAssessWeight")
    @Operation(summary = "教学目标绑定考核点")
    public Result<String> saveAssessWeight(@RequestBody List<ArchiveAssessByCourseIdVo> assess) {
        System.out.println("判断：");
        System.out.println(assess);
        archiveAssessService.saveAssessWeight(assess);
        return Result.ok();
    }

    @PostMapping("/saveEvaluation")
    @Operation(summary = "更新评价依据")
    public Result<String> saveEvaluation(@RequestBody ArchivePointAndTargetVO assess) {
        System.out.println(assess);
        archiveAssessService.saveEvaluation(assess);
        return Result.ok();
    }

    @PostMapping("/getWeightSum")
    @Operation(summary = "获取权重富余")
    public Result<BigDecimal> getWeightSum(@RequestBody ArchiveAssessByCourseIdVo assess) {
        BigDecimal sum = archiveAssessService.getWeightSum(assess);
        System.out.println(sum);
        if(sum != null) {
            BigDecimal Bsum = sum.setScale(2);
            BigDecimal One = new BigDecimal(1.00);
            System.out.println("测试：");
            System.out.println(One.subtract(Bsum));
            return Result.ok(One.subtract(Bsum));
        } else {
            return Result.ok(null);
        }
    }

    @GetMapping("/getMannerWeight")
    @Operation(summary = "获取考核方式权重富余")
    public Result<BigDecimal> getMannerWeight(@RequestParam String courseId) {
        BigDecimal sumMore = archiveAssessService.getMannerWeight(courseId);
        System.out.println("考核方式测试");
        System.out.println(sumMore);
        return Result.ok(sumMore);
    }

    @PostMapping("/getWeightTable")
    @Operation(summary = "获取第二步表格相关")
    public Result<ArchiveAssessTableVo> getWeightTable(@RequestBody ArchiveAssessByCourseIdVo assess) {
        ArchiveAssessTableVo list = archiveAssessService.getWeightTable(assess);
        return Result.ok(list);
    }

    @GetMapping("/getWeightTableStep4")
    @Operation(summary = "获取第四步第一个表格")
    public Result<ArchiveAssessTableVo> getWeightTableStep4(@RequestParam Long courseId) {
        ArchiveAssessTableVo list = archiveAssessService.getWeightTableStep4(courseId);
        return Result.ok(list);
    }

    @GetMapping("getArchiveAssess")
    @Operation(summary = "获取考核点权重-旧")
    public Result<List<ArchiveAssessVO>> getArchiveAssess(@RequestParam("id")String  id){
        List<ArchiveAssessVO> archiveAssessVO=archiveAssessService.selectWeightById(id);
        return Result.ok(archiveAssessVO);

    }
    @GetMapping("getArchiveAssessList")
    @Operation(summary = "获取考核点权重-新")
    public Result<List<ArchiveAssessScoreBookWeightList>> getArchiveAssessList(@RequestParam("id")String  id){
        List<ArchiveAssessScoreBookWeightList> list=archiveAssessService.selectWeightByIdNew(id);
        return Result.ok(list);

    }


}
