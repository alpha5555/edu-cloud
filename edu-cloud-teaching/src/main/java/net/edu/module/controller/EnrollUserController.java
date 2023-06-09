package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.convert.EnrollUserConvert;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.service.EnrollUserService;
import net.edu.module.vo.EnrollUserVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* XinXiHeShi
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-05
*/
@RestController
@RequestMapping("enrollUser")
@Tag(name="XinXiHeShi")
@AllArgsConstructor
@Slf4j
public class EnrollUserController {
    private final EnrollUserService enrollUserService;

    @GetMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<EnrollUserVO>> page(@Valid EnrollUserQuery query){
        PageResult<EnrollUserVO> page = enrollUserService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    public Result<EnrollUserVO> get(@PathVariable("id") Long id){
        EnrollUserEntity entity = enrollUserService.getById(id);

        return Result.ok(EnrollUserConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody EnrollUserVO vo){
        System.out.println(vo);
        enrollUserService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid EnrollUserVO vo){
        enrollUserService.update(vo);

        return Result.ok();
    }


    @DeleteMapping
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Long> idList){
        enrollUserService.delete(idList);

        return Result.ok();
    }

    @GetMapping("confirm")
    @Operation(summary = "更新状态")
    public Object confirm(@RequestParam("id") Integer id){
        enrollUserService.confirm(id);
        return Result.ok();
    }

    @GetMapping("insertClassUser")
    @Operation(summary = "添加班级学生")
    public Result<String> insertClassUser(@RequestParam("classId") Integer classId, @RequestParam("userId") Integer userId){
        enrollUserService.insertClassUser(classId,userId);
        return Result.ok();
    }

    @PostMapping("insertOpenId")
    @Operation(summary = "添加班级学生openId")
    public Result<String> insertOpenId(String openId,String unionId){
        enrollUserService.insertOpenId(openId,unionId);
        return Result.ok();
    }

    @PostMapping("post")
    @Operation(summary = "修改班级学生")
    public Result<String> post(@RequestBody  EnrollUserVO enrollUserVO){
        log.info(enrollUserVO.toString());
        enrollUserService.post(enrollUserVO);
        return Result.ok();
    }

    @PostMapping("insertEnrollUser")
    @Operation(summary = "新增班级学生")
    public Result<Integer> insertEnrollUser(@RequestBody  EnrollUserVO enrollUserVO){
        int userId= enrollUserService.insertEnrollUser(enrollUserVO);
        return Result.ok(userId);
    }

    @GetMapping("info")
    public Result<EnrollUserVO> getUserInfo(@RequestParam("openId") String openId){
        System.out.println("info"+openId);
        return Result.ok(enrollUserService.selectUserInfoByOpenId(openId));
    }

    @GetMapping("insertSysUser")
    public Result<String> insertSysUser(@RequestParam("id") Integer id) {
        System.out.println(id);
        enrollUserService.insertSysUser(id);
        return Result.ok();
    }
}
