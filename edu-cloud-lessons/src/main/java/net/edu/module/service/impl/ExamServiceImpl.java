package net.edu.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ExamConvert;
import net.edu.module.dao.ExamDao;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamQuery;
import net.edu.module.service.ExamService;
import net.edu.module.vo.ExamVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
@Service
@AllArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamDao, ExamEntity> implements ExamService {

    @Override
    public PageResult<ExamVO> page(ExamQuery query) {
        Page<ExamVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ExamVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public void save(ExamVO vo) {
        ExamEntity entity = ExamConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ExamVO vo) {
        ExamEntity entity = ExamConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}