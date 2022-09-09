package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import java.util.Date;

/**
* 班级发布
*
* @author 翁瑞辰 
* @since  2022-09-06
*/
@Data
@Schema(description = "班级发布")
public class EnrollClassVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "假班级id")
	private Integer id;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建人id")
	private Integer creator;

	@Schema(description = "更新人id")
	private Integer updater;

	@Schema(description = "版本号")
	private Integer version;

	@Schema(description = "0没有发布，1已经发布")
	private Integer status;

	@Schema(description = "班级名称")
	private String className;

	@Schema(description = "课程类型")
	private String courseType;

	@Schema(description = "开始时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private String startTime;

	@Schema(description = "结束时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private String endTime;

	@Schema(description = "面向对象")
	private String object;

	@Schema(description = "课程目标")
	private String target;

	@Schema(description = "是否删除")
	private Integer deleted;

	@Schema(description = "开课日期")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private String classDate;

	@Schema(description = "结课日期")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private String classEnd;
}