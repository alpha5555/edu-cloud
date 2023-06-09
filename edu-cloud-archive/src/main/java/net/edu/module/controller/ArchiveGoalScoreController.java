package net.edu.module.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.query.ArchiveTargetQuery;
import net.edu.module.service.ArchiveAssessScoreService;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.vo.ArchiveAssessScoreVO;
import net.edu.module.vo.ArchiveGoalPeopleVO;
import net.edu.module.vo.ArchiveGoalScoreVO;
import net.edu.module.vo.ArchiveTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.edu.module.vo.ArchiveGoalScoreVO;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveGoalScoreInBooKVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 考试成绩表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-11-16
*/
@RestController
@RequestMapping("goal/score")
@Tag(name="考试成绩表")
@AllArgsConstructor
public class ArchiveGoalScoreController {
    @Autowired
    private ArchiveGoalScoreService archiveGoalScoreService;
    @GetMapping("general")
    @Operation(summary = "获取总评分数")
    public Result<List<ArchiveGoalScoreVO>> getScore(@RequestParam("summaryId") Long summaryId,@RequestParam("courseId") Long courseId){
        return Result.ok(archiveGoalScoreService.selectGoalScoreByCourseId(summaryId,courseId));
    }


    @GetMapping("generalByStudentId")
    @Operation(summary = "获取学生总评成绩")
    public Result<String> getScoreByStudentId(@RequestParam("summaryId") String summaryId,@RequestParam("courseId") String courseId,@RequestParam("studentId")String studentId){
        return Result.ok(archiveGoalScoreService.getScoreByStudentId(summaryId,courseId,studentId));
    }

    @PostMapping
    @Operation(summary = "插入")
    public Result<String> save(@RequestBody List<ArchiveGoalScoreVO> vo){
        System.out.println(vo);
        archiveGoalScoreService.insertGoalScore(vo);
        return Result.ok();
    }

    @GetMapping("sample")
    @Operation(summary = "样本分析")
    public Result<List<ArchiveGoalPeopleVO>> sample(@RequestParam("summaryId") Long summaryId){
        List<ArchiveGoalPeopleVO> sample = archiveGoalScoreService.getSample(summaryId);
        return Result.ok(sample);
    }
    @PostMapping("grade")
    @Operation(summary = "获取成绩表")
    public Result<ArchiveGoalScoreInBooKVO> getGradeInfo(@RequestBody JSONObject jsonObject){
        JSONObject classInfo=JSONUtil.parseObj(jsonObject.get("classInfo"))  ;
        String id= String.valueOf(jsonObject.get("id"));
        System.out.println(classInfo);
        ArchiveGoalScoreInBooKVO archiveGoalScoreInBooKVO =archiveGoalScoreService.getGradeInfo(classInfo,id);
        return Result.ok(archiveGoalScoreInBooKVO);
    }

    @GetMapping("unit")
    @Operation(summary = "个体分析")
    public Result<List<ArchiveGoalScoreVO>> unit(@RequestParam("summaryId") Long summaryId){
        List<ArchiveGoalScoreVO> unit = archiveGoalScoreService.getUnit(summaryId);
        return Result.ok(unit);
    }
}
