package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 能力课程
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_course")
public class ArchiveCourseEntity {
	/**
	* 课程编号
	*/
	@TableId
	private Long id;

	/**
	* 外部系统编号
	*/
	private Long sysId;

	/**
	* 名称
	*/
	private String name;

	/**
	* 学时
	*/
	private String creditHours;

	/**
	* 周学时
	*/
	private String weeklyCreditHours;

	/**
	* 课程类别
	*/
	private String courseCategory;

	/**
	* 课程类型
	*/
	private String type;

	/**
	* 开设学期
	*/
	private String semester;

	/**
	* 年级
	*/
	private String grade;

	/**
	* 删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	* 修改时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	* 创建人
	*/
	@TableField(fill = FieldFill.INSERT)
	private String creator;

	/**
	* 版本号
	*/
	private Integer version;

	/**
	* 修改人
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updater;

}