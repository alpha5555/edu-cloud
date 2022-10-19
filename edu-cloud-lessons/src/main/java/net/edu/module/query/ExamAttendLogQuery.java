package net.edu.module.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.common.query.Query;

/**
 * 考试参加表查询
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Schema(description = "考试参加表查询")
public class ExamAttendLogQuery extends Query {
    private Long examId;

    private String name;

    private Integer status;

    private Integer isCorrecting;
}