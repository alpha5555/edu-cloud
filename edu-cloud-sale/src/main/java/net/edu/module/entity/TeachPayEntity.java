package net.edu.module.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 流水账管理
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_pay")
public class TeachPayEntity {
	/**
	* 缴费记录id
	*/
	@TableId
	private Integer id;

	/**
	* 应付金额
	*/
	private BigDecimal payment;

	/**
	 * 实付金额
	 */
	private BigDecimal payable;

	/**
	 * 优惠金额
	 */
	private BigDecimal discount;

	/**
	 * 购买课次
	 */
	private BigDecimal classHours;

	/**
	 * 赠送课次
	 */
	private BigDecimal presentHours;

	/**
	* 用户id
	*/
	private Long userId;

	/**
	* 缴费时间
	*/
	private Date time;

	/**
	* 备注
	*/
	private String bz;

	/**
	 * 经手人
	 */
	private String handler;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 购买课程类型
	 */
	private Integer classType;

	/**
	 * 赠送课程类型
	 */
	private Integer presentType;

}