package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.service.StudentLessonService;
import net.edu.module.vo.StudentsStatisticsInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/15 9:33
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("stu")
@AllArgsConstructor
public class StudentAttendController {

    private final StudentLessonService studentLessonService;

    @GetMapping("{lessonId}")
    @Operation(summary = "参加课程")
    public Result<String> attendLesson( @PathVariable Long lessonId){
        return studentLessonService.attendLesson(lessonId);
    }


    @GetMapping("exam/{examId}")
    @Operation(summary = "参加考试")
    public Result<Object> attendExam( @PathVariable Long examId){
        return studentLessonService.attendExam(examId);
    }

}
