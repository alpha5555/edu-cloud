package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分配角色查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "分配角色查询")
public class RoleQuery extends TeacherQuery {
    @Schema(description = "角色ID")
    private Long roleId;

}
