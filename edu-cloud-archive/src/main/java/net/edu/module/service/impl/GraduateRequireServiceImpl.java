package net.edu.module.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.GraduateRequireConvert;
import net.edu.module.dao.ArchiveTargetDao;
import net.edu.module.dao.ArchiveWeightTargetAssessDao;
import net.edu.module.dao.ArchiveWeightTargetCourseDao;
import net.edu.module.dao.GraduateRequireDao;
import net.edu.module.entity.GraduateRequireEntity;
import net.edu.module.query.GraduateRequireQuery;
import net.edu.module.service.GraduateRequireService;
import net.edu.module.vo.ArchiveTargetVO;
import net.edu.module.vo.GraduateRequireVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 毕业要求
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-20
 */
@Service
@AllArgsConstructor
public class GraduateRequireServiceImpl extends BaseServiceImpl<GraduateRequireDao, GraduateRequireEntity> implements GraduateRequireService {

    @Autowired
    private GraduateRequireDao graduateRequireDao;
    @Autowired
    private ArchiveWeightTargetAssessDao archiveWeightTargetAssessDao;
    @Autowired
    private ArchiveWeightTargetCourseDao archiveWeightTargetCourseDao;
    @Autowired
    private ArchiveTargetDao archiveTargetDao;

    @Override
    public PageResult<GraduateRequireVO> page(GraduateRequireQuery query) {
        try {
            query.setTitle(java.net.URLDecoder.decode
                    (query.getTitle(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Page<GraduateRequireVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<GraduateRequireVO> list = graduateRequireDao.selectGraduateRequireByPage(page, query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    private LambdaQueryWrapper<GraduateRequireEntity> getWrapper(GraduateRequireQuery query) {
        LambdaQueryWrapper<GraduateRequireEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(GraduateRequireVO vo) {
        graduateRequireDao.insertGraduateRequire(vo);
    }

    @Override
    public void update(GraduateRequireVO vo) {
        GraduateRequireEntity entity = GraduateRequireConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        for (Long id : idList
        ) {
//           删除毕业要求表
            graduateRequireDao.deleteGraduate(id);
            List<ArchiveTargetVO> archiveTargetVOS = archiveTargetDao.selectArchiveTargetByGraduateId(id);
//            删除指标点表
            archiveTargetDao.updateDeleted(id);
            for (int i = 0; i < archiveTargetVOS.size(); i++) {
                Long targetId = archiveTargetVOS.get(i).getId();
//                删除关联表
                archiveWeightTargetCourseDao.updateDeletedByTarget(targetId);
            }
        }

    }

    @Override
    public List<GraduateRequireEntity> selectGraduateByGrade(String grade) {
        return graduateRequireDao.selectGraduateByGrade(grade);
    }

    @SneakyThrows
    @Override
    public void importArchive(MultipartFile file) {
        List<GraduateRequireVO> list = EasyExcel.read(file.getInputStream()).head(GraduateRequireVO.class)
                .sheet().headRowNumber(2).doReadSync();
        for (GraduateRequireVO vo : list) {
            System.out.println(vo);
            save(vo);
        }
    }

    @Override
    public void saveBatchRequire(GraduateRequireVO vo) {
        Long graduateId = vo.getId();
        save(vo);
        System.out.println("1"+vo.getId());
        List<ArchiveTargetVO> list = archiveTargetDao.selectArchiveTargetByGraduateId(graduateId);
        System.out.println("2"+list);
        for (ArchiveTargetVO archiveVOS : list
        ) {
            archiveVOS.setGrade(vo.getGrade());
            archiveVOS.setGraduateId(vo.getId());
            archiveTargetDao.insertArchiveTarget(archiveVOS);
        }

    }

    @Override
    public List<GraduateRequireVO> selectWeight(Long id) {
        return graduateRequireDao.selectWeight(id);
    }

}
