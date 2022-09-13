package net.edu.module.api;


import net.edu.framework.common.utils.Result;
import net.edu.module.api.fallback.EduProblemFallBack;
import net.edu.module.vo.CodeSampleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "edu-cloud-problem", fallbackFactory = EduProblemFallBack.class)
public interface EduProblemApi {

    //获取测试样例
    @GetMapping("sample/list/{problemId}")
    Result<List<CodeSampleVO>> getSampleList(@PathVariable(value = "problemId") Long  problemId);

    @GetMapping("choice/options/{problemId}")
    List<String> getChoiceOptions(@PathVariable(value = "problemId") Long problemId,@RequestParam(required = false)int flag);

    @GetMapping("choice/submitTimes")
    Result<String> updateChoiceSubmitTimes(@RequestParam Long id , @RequestParam Boolean isTrue );

    @GetMapping("code/submitTimes")
    Result<String> updateCodeSubmitTimes(@RequestParam Long id , @RequestParam Boolean isTrue );

    @GetMapping("fill/submitTimes")
    Result<String> updateFillSubmitTimes(@RequestParam Long id , @RequestParam Boolean isTrue );
}
