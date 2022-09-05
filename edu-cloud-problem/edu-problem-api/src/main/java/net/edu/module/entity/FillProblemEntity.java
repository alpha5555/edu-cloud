package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 填空题表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_fill")
public class FillProblemEntity {
	@TableId
	private Integer id;

	/**
	* 名称
	*/
	private String name;

	/**
	* 说明
	*/
	private String description;

	/**
	* 来源
	*/
	private String source;

	/**
	* 提示
	*/
	private String tips;

	/**
	* 建议用时
	*/
	private Integer adviceTime;

	/**
	* 图片
	*/
	private String img;

	/**
	* 难度
	*/
	private Integer difficulty;

	/**
	* 知识点
	*/
	private Integer kpId;

	/**
	* 提交次数
	*/
	private Integer submitTimes;

	/**
	* 正确次数
	*/
	private Integer correctTimes;

	/**
	* 典型问题
	*/
	private Integer isTypical;

	/**
	* 引用次数
	*/
	private Integer usedNum;

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
	* 操作人
	*/
	private String operator;

	/**
	* 是否删除
	*/
	private String isDeleted;

	/**
	* 参考答案
	*/
	private String answer;

	/**
	* 状态
	*/
	private Integer state;

}