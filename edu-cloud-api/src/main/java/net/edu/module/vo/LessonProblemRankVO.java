package net.edu.module.vo;

import lombok.Data;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/14 20:10
 * @Version: 1.0
 * @Description:
 */
@Data
public class LessonProblemRankVO {
    private Long userId;
    private Long lessonId;
    private String lessonName;
    private String name;
    private Integer rankNum;
    private Integer unansweredNum;
    private Integer answeredNum;
    private Integer undecidedNum;
    private Integer correctNum;
    private Integer errorNum;
    private String content;
}
