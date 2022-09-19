package net.edu.module.service;

import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.api.vo.SampleVO;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.vo.CodeSampleVO;

import java.util.List;

/**
 * 测试样例表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-07
 */
public interface CodeSampleService extends BaseService<CodeSampleEntity> {

    List<CodeSampleVO> getList(Long problemId);


    void update(CodeSampleVO vo);

    void delete(List<Long> idList);


    void saveSample(List<SampleVO> sampleVOS, Long problemId);
}