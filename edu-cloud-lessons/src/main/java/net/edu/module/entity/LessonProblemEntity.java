package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 课堂练习表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_problem")
public class LessonProblemEntity {
	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 课堂ID
	 */
	private Long lessonId;

	/**
	 * 问题ID
	 */
	private Long problemId;

	/**
	 * 问题名称（冗余，提高效率）
	 */
	private String problemName;

	/**
	 * 问题类型(选择填空代码)
	 */
	private Integer problemType;

	/**
	 * 开始时间
	 */
	private Date beginTime;

	/**
	 * 分数
	 */
	private Integer score;

	/**
	 * 顺序
	 */
	private Integer sort;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 来源
	 */
	private Integer source;

	/**
	 * 练习类型
	 */
	private Integer type;
	/**
	 * 是否使用
	 */
	private Integer used;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

}