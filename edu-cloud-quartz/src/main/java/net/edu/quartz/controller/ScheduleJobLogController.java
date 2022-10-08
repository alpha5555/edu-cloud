package net.edu.quartz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.quartz.convert.ScheduleJobLogConvert;
import net.edu.quartz.entity.ScheduleJobLogEntity;
import net.edu.quartz.query.ScheduleJobLogQuery;
import net.edu.quartz.service.ScheduleJobLogService;
import net.edu.quartz.vo.ScheduleJobLogVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
* 定时任务日志
*
* @author 阿沐 babamu@126.com
*/
@RestController
@RequestMapping("log")
@Tag(name="定时任务日志")
@AllArgsConstructor
public class ScheduleJobLogController {
    private final ScheduleJobLogService scheduleJobLogService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('schedule:log')")
    public Result<PageResult<ScheduleJobLogVO>> page(@Valid ScheduleJobLogQuery query){
        PageResult<ScheduleJobLogVO> page = scheduleJobLogService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('schedule:log')")
    public Result<ScheduleJobLogVO> get(@PathVariable("id") Long id){
        ScheduleJobLogEntity entity = scheduleJobLogService.getById(id);

        return Result.ok(ScheduleJobLogConvert.INSTANCE.convert(entity));
    }

}