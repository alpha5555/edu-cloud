package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* XinXiHeShi
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-05
*/
@Data
@Schema(description = "XinXiHeShi")
public class EnrollUserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "用户id")
	private Integer id;

	@Schema(description = "微信昵称")
	private String wxName;

	@Schema(description = "真实姓名")
	private String realName;

	@Schema(description = "手机号码")
	private String phone;

	@Schema(description = "unionId")
	private String unionId;

	@Schema(description = "openId(用户对微信公众号唯一标识)")
	private String openId;

	@Schema(description = "所在区域")
	private String area;

	@Schema(description = "详细地址")
	private String address;

	@Schema(description = "年龄")
	private Integer age;

	@Schema(description = "年级")
	private Integer grade;

	@Schema(description = "意向说明")
	private String purpose;

	@Schema(description = "1为报名信息状态,3成为学员")
	private Integer status;

	@Schema(description = "注册时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "修改时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "推荐人")
	private Long reference;


}
