package net.edu.framework.common.utils;



import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.StringUtils;
import net.edu.framework.common.excel.ExcelAsyncDataListener;
import net.edu.framework.common.excel.ExcelFinishCallBack;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/15 10:24
 * @Version: 1.0
 * @Description:
 */
public class ExcelUtils {
    /**
     * 判断excel文件类型
     *
     * @param file 源头文件
     * @return type
     */
    public static ExcelTypeEnum getExcelFileType(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (StringUtils.isNotBlank(filename)) {
            filename = filename.substring(filename.lastIndexOf("."));
            switch (filename) {
                case ".csv":
                    return ExcelTypeEnum.CSV;
                case ".xls":
                    return ExcelTypeEnum.XLS;
                case ".xlsx":
                    return ExcelTypeEnum.XLSX;
                default:
                    throw new IllegalArgumentException("无效的文件");
            }
        }
        throw new IllegalArgumentException("无效的文件");
    }

    /**
     * 读取excel文件
     *
     * @param <T>      数据类型
     * @param file     excel文件
     * @param head     列名
     * @param callBack 回调 导入时传入定义好的回调接口，excel数据解析完毕之后监听器将数据传入回调函数
     *                 这样调用工具类时可以通过回调函数获取导入的数据，如果数据量过大可根据实际情况进行分配入库
     */
    public static <T> void readAnalysis(MultipartFile file, Class<T> head, ExcelFinishCallBack<T> callBack) {
        try {
            EasyExcel.read(file.getInputStream(), head, new ExcelAsyncDataListener<>(callBack)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel文件
     *
     * @param <T>      数据类型
     * @param file     excel文件
     * @param head     列名
     * @param callBack 回调 导入时传入定义好的回调接口，excel数据解析完毕之后监听器将数据传入回调函数
     *                 这样调用工具类时可以通过回调函数获取导入的数据，如果数据量过大可根据实际情况进行分配入库
     */
    public static <T> void readAnalysis(File file, Class<T> head, ExcelFinishCallBack<T> callBack) {
        try {
            EasyExcel.read(new FileInputStream(file), head, new ExcelAsyncDataListener<>(callBack)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel文件 同步
     *
     * @param <T>   数据类型
     * @param file  文件
     * @param clazz 模板类
     * @return java.util.List list
     */
    public static <T> List<T> readSync(File file, Class<T> clazz) {
        return readSync(file, clazz, 1, 0, ExcelTypeEnum.XLSX);
    }


    /**
     * 读取excel文件 同步 自定义监听器
     * @param file
     * @param readListener
     * @param rowNum
     * @param sheetNo
     */
    public static void readSync(File file, ReadListener readListener, Integer rowNum, Integer sheetNo) {
        EasyExcel.read(file, readListener).sheet(sheetNo).headRowNumber(rowNum).doRead();
    }


    /**
     * 读取excel文件 同步 自定义监听器
     * @param file
     * @param readListener
     * @param rowNum
     * @param sheetNo
     */
    public static void readSync(MultipartFile file, ReadListener readListener, Integer rowNum, Integer sheetNo) {
        try {
            EasyExcel.read(file.getInputStream(), readListener).sheet(sheetNo).headRowNumber(rowNum).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 读取excel文件 同步
     *
     * @param <T>       数据类型
     * @param file      文件
     * @param clazz     模板类
     * @param rowNum    数据开始行 1
     * @param sheetNo   第几张表
     * @param excelType 数据表格式类型
     * @return java.util.List list
     */
    public static <T> List<T> readSync(File file, Class<T> clazz, Integer rowNum, Integer sheetNo, ExcelTypeEnum excelType) {
        return EasyExcel.read(file).headRowNumber(rowNum).excelType(excelType).head(clazz).sheet(sheetNo).doReadSync();
    }


    /**
     * 读取excel文件 同步
     *
     * @param <T>       数据类型
     * @param file      文件
     * @param clazz     模板类
     * @param rowNum    数据开始行 1
     * @param sheetNo   第几张表
     * @param excelType 数据表格式类型
     * @return java.util.List list
     */
    public static <T> List<T> readSync(MultipartFile file, Class<T> clazz, Integer rowNum, Integer sheetNo, ExcelTypeEnum excelType) {
        try {
            return EasyExcel.read(file.getInputStream()).headRowNumber(rowNum).excelType(excelType).head(clazz).sheet(sheetNo).doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 导出数据到文件
     *
     * @param <T>  数据类型
     * @param head 类名
     * @param file 导入到文件
     * @param data 数据
     */
    public static <T> void excelExport(Class<T> head, File file, List<T> data) {
        excelExport(head, file, "sheet1", data);
    }

    /**
     * 导出数据到文件
     *
     * @param <T>       写入格式
     * @param head      类名
     * @param file      写入到文件
     * @param sheetName sheet名称
     * @param data      数据列表
     */
    public static <T> void excelExport(Class<T> head, File file, String sheetName, List<T> data) {
        try {
            EasyExcel.write(file, head).sheet(sheetName).doWrite(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
