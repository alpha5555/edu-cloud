package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 课堂签到表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Data
@Schema(description = "课堂签到表")
public class LessonAttendLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "学生id")
    private Long stuId;

    @Schema(description = "学生姓名")
    private String name;

    @Schema(description = "学号")
    private String studentNumber;

    @Schema(description = "课堂id")
    private Long lessonId;

    @Schema(description = "签到时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date checkinTime;

    @Schema(description = "课堂状态，0=请假，1=签到")
    private Integer status;

    @Schema(description = "是否点名")
    private Integer rollCall;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    private String ability;

    private String abilityItem;

    private Integer submitTimes;

    private Integer correctTimes;

    private Boolean onlineFlag;

    private Integer likesNum;

    private String sysEvaluation;

    private String teaEvaluation;


}