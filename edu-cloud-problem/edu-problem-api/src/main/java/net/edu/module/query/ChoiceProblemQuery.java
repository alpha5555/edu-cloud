package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;


import java.util.Date;

/**
* 选择题库表查询
*
* @author 马佳浩 
* @since 1.0.0 2022-09-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "选择题库表查询")
public class ChoiceProblemQuery extends Query {
    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "难度")
    private Integer difficulty;

    @Schema(description = "知识点")
    private Long kpId;

    @Schema(description = "状态")
    private Integer state;

}