package net.edu.module.utils;


import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.excel.HeadContentCellStyle;
import net.edu.framework.common.utils.ResponseHeadUtils;
import net.edu.module.vo.LessonJudgeRecordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 樊磊
 * @Date: 2022/11/08 17:55
 * @Version: 1.0
 * @Description:课堂excel相关工具类
 */
@Component
@Slf4j
public class LessonExcelUtil {

    public static void examExportExcel(List<String> header, List<LessonJudgeRecordVo> data, String bigTitle, HttpServletResponse response) throws IOException {
        String name = StringUtils.substringBetween(bigTitle, "《", "》")+ "成绩详情.xlsx";
        ResponseHeadUtils.responseEXCELHead(response,name);
        EasyExcel.write(response.getOutputStream())
                .head(getExcelHeader(header, bigTitle))
                .registerWriteHandler(new CellRowHeightStyleStrategy())
                .registerWriteHandler(HeadContentCellStyle.myHorizontalCellStyleStrategy())
                .sheet(name).doWrite(getExamExcelData(data));
    }

    /**
     * 生成表头
     *
     * @param header
     * @param bigTitle
     * @return
     */
    public static List<List<String>> getExcelHeader(List<String> header, String bigTitle) {
        List<List<String>> head = new ArrayList<>();
        List<String> childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("学号");
        head.add(childHead);
        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("姓名");
        head.add(childHead);

        for (String i : header) {
            childHead = new ArrayList<>();
            childHead.add(bigTitle);
            childHead.add(i);
            head.add(childHead);
        }
        childHead = new ArrayList<>();
        childHead.add(bigTitle);
        childHead.add("总数");
        head.add(childHead);
        return head;
    }

    /**
     * 生成表格数据
     *
     * @param vo
     * @return
     */
    public static List<List<String>> getExamExcelData(List<LessonJudgeRecordVo> vo) {
        List<List<String>> dataList = new ArrayList<>();
        for (int i = 0; i < vo.size(); i++) {
            List<String> list = new ArrayList<>();
            String username = vo.get(i).getUsername();
            list.add(username);
            int sum = 0;
            list.add(vo.get(i).getName());
            for (int j = 0; j < vo.get(i).getProblemRecords().size(); j++) {
                if(vo.get(i).getProblemRecords().get(j).getSubmitStatus()!=null){
                    Integer status = vo.get(i).getProblemRecords().get(j).getSubmitStatus();
                    if (status==0){
                        list.add("未判题");
                    }else if(status==3){
                        list.add("正确");
                        sum = sum +1;
                    }else {
                        list.add("错误");
                    }
                }else {
                    list.add(" ");
                }
            }
            list.add(String.valueOf(sum));
            dataList.add(list);
        }
        return dataList;
    }
}
