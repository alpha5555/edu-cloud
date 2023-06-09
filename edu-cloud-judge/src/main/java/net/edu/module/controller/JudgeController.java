package net.edu.module.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.JudgeService;
import net.edu.module.service.RecordService;
import net.edu.module.vo.JudgeRecordSubmitVO;
import net.edu.module.vo.lesson.LessonJudgeRecordVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/4 1:02
 * @Version: 1.0
 * @Description:
 */
@RestController
@Tag(name = "代码题判题接口")
@AllArgsConstructor
public class JudgeController {
    private final JudgeService judgeService;

    private final RecordService recordService;

    @PostMapping("/record")
    public Result<Integer> judge(@RequestBody JudgeRecordSubmitVO vo) {

        vo.setSubmitStatus(0);
        vo.setUserId(SecurityUser.getUserId());
        if (StrUtil.isEmpty(vo.getSubmitImg())) {
            vo.setSubmitImg(null);
        }
        judgeService.judgeBefore(vo);
        return Result.ok();
    }

    @PostMapping("/getRecord")
    @Operation(summary = "获取学生答题记录，回显")
    public Result<JudgeRecordSubmitVO> getRecord(@RequestBody JudgeRecordSubmitVO vo) {
        return Result.ok(recordService.getRecord(vo));
    }


    /**
     * 获取学生答题记录
     * @param vo
     * @return
     */
    @PostMapping("/getRecordAndAnswer")
    @Operation(summary = "获取学生答题记录")
    public Result<JudgeRecordSubmitVO> getRecordAndAnswer(@RequestBody JudgeRecordSubmitVO vo){
        return Result.ok(recordService.getRecordAndAnswer(vo));
    }

    @PostMapping("/updateReasonAndStatus")
    @Operation(summary = "更新判题备注和提交状态（改判）")
    public Result<String> updateReasonAndStatus(@RequestBody JudgeRecordSubmitVO vo){
        recordService.updateReasonAndStatus(vo);
        return Result.ok();
    }


    @GetMapping("/statisticsUserJudgeRecord")
    @Operation(summary = "统计用户所有答题记录")
    public Result<String> statisticsUserJudgeRecord(){
        recordService.statisticsUserJudgeRecord();
        return Result.ok();
    }


    @GetMapping("/getFilePath")
    @Operation(summary = "获取附件（文件）路径")
    public Result<List<String>> getFilePath(@RequestParam("problemId") Long problemId,
                                           @RequestParam("problemType") Integer problemType,
                                           @RequestParam("source") Integer source,
                                           @RequestParam("sourceId") Long sourceId){
        return Result.ok(judgeService.getFilePath(problemId, problemType, source, sourceId));
    }

}
