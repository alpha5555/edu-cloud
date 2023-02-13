package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 教学试听安排
*
* @author sqw 
* @since 1.0.0 2023-02-13
*/
@Data
@Schema(description = "教学试听安排")
public class TeachAuditionRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增id")
	private Long id;

	@Schema(description = "学生id")
	private Long studentId;

	@Schema(description = "学生姓名")
	private String studentName;

	@Schema(description = "班级id")
	private Long lessonId;

	@Schema(description = "课堂名称")
	private String lessonName;

	@Schema(description = "安排时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date dateTime;

	@Schema(description = "是否已安排试听,0未安排，1安排")
	private Integer status;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "版本")
	private Integer version;

	@Schema(description = "修改时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建人")
	private Long creator;

	@Schema(description = "更新人")
	private Long updater;

	@Schema(description = "删除")
	private Integer deleted;


}