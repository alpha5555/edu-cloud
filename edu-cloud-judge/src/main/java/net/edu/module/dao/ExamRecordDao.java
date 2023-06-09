package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.module.query.ExamRecordQuery;
import net.edu.module.vo.exam.ExamProblemRecord;
import net.edu.module.vo.exam.ExamScoreVO;
import net.edu.module.vo.exam.ExamUserExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/18 9:39
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface ExamRecordDao {
   ExamScoreVO selectUserExamScore(Long examId, Long userId);

   List<ExamScoreVO> selectExamRecordList( @Param("query")ExamRecordQuery query);

   int updateProblemScore(BigDecimal score,Long id);

   List<ExamUserExcelVo> selectExamProblemInfoList(@Param("examId") Long examId,@Param("userIdList") List<Long> userIdList);
}
