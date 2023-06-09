package net.edu.module.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.FillProblemConvert;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.FillProblemQuery;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.FillProblemVO;
import net.edu.module.dao.FillProblemDao;
import net.edu.module.service.FillProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 填空题表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class FillProblemServiceImpl extends BaseServiceImpl<FillProblemDao, FillProblemEntity> implements FillProblemService {


    private final RedisUtils redisUtils;

    @Override
    public PageResult<FillProblemVO> page(FillProblemQuery query) {
        Page<FillProblemVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<FillProblemVO> list = baseMapper.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public void save(FillProblemVO vo) {
        FillProblemEntity entity = FillProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(FillProblemVO vo) {
        FillProblemEntity entity = FillProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
        redisUtils.del(RedisKeys.getProblemInfo(vo.getId(), "fill"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {

        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long problemId) {
        baseMapper.updateStatus(problemId);
    }


    @Override
    public void updateUsedNum(Long id) {
        baseMapper.updateUsedNum(id);

    }

    @Override
    public void updateSubmitTimes(Long id, Boolean isTrue) {
        baseMapper.updateSubmitTimes(id, isTrue);

    }

    @Override
    public FillProblemVO selectFillProblemInfo(Long id) {
        FillProblemVO fillProblemVO = (FillProblemVO) redisUtils.get(RedisKeys.getProblemInfo(id, "fill"));
        if (fillProblemVO == null) {
            fillProblemVO = baseMapper.selectFillProblemInfo(id);
            redisUtils.set(RedisKeys.getProblemInfo(id, "fill"), fillProblemVO, RedisUtils.MIN_TEN_EXPIRE);
        }
        return fillProblemVO;
    }

    @SneakyThrows
    @Override
    public void importFromExcel(MultipartFile file) {

        List<FillProblemVO> list= ExcelUtils.readSync(file,FillProblemVO.class,3,0, ExcelUtils.getExcelFileType(file));
        if(list!=null){
            for (FillProblemVO vo:list){
                save(vo);
            }
        }
    }
}