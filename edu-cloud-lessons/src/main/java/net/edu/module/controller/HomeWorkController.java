package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.query.HomeWorkQuery;
import net.edu.module.service.HomeWorkService;
import net.edu.module.vo.HomeWorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月05日 19:09
 */
@RestController
@RequestMapping("homeWork")
@Tag(name="回家作业")
@AllArgsConstructor
@Slf4j
public class HomeWorkController {
    @Autowired
    private final HomeWorkService homeWorkService;

    /**
     * 根据学生id分页获取对应课后作业
     *
     */
    @GetMapping("/homeWorkPage")
    public Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@Valid HomeWorkQuery query){

        PageResult<HomeWorkVO> page=homeWorkService.getStudentHomeWorkPage(query);
        return Result.ok(page);

    }

    /**
     * 改变完成状态
     */
    @GetMapping("/changeProblemStatus")
    public void changeChoiceProblemStatus(String problemId){
        homeWorkService.changeProblemStatus(problemId);
    }
}
