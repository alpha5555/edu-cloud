package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 选择题库表
*
* @author 马佳浩
* @since 1.0.0 2022-09-05
*/
@Data
@Schema(description = "选择题库表")
public class ChoiceProblemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "问题ID")
	private Long id;

	@Schema(description = "类型")
	private Integer type;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "问题说明")
	private String description;

	@Schema(description = "来源")
	private String source;

	@Schema(description = "提示")
	private String tips;

	@Schema(description = "建议用时")
	private Integer adviceTime;

	@Schema(description = "图片")
	private String img;

	@Schema(description = "难度")
	private Integer difficulty;

	@Schema(description = "知识点代码")
	private String kpCode;

	@Schema(description = "知识点名称")
	private String  kpName;

	@Schema(description = "回答次数")
	private Integer submitTimes;

	@Schema(description = "正确次数")
	private Integer correctTimes;

	@Schema(description = "典型问题")
	private Integer isTypical;

	@Schema(description = "选项")
	private List<ChoiceOptionVO> options;

	@Schema(description = "选项数")
	private Integer optionNum;

	@Schema(description = "引用次数")
	private Integer usedNum;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;


	@Schema(description = "创建人")
	private Integer creator;


	@Schema(description = "状态")
	private Integer status;


}
