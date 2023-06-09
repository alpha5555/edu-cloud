package net.edu.module.utils;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.xpath.operations.Bool;

import java.util.List;

public class WidthStyle extends AbstractColumnWidthStyleStrategy {
    private final int width;
    private final List<Integer> col;

    public WidthStyle(int width,List<Integer> col) {
        this.width = width;
        this.col = col;
//        for (int i = 0; i < col.size(); i++) {
//            System.out.println("123456789"+col.get(i));
//        }

    }

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        Sheet sheet = writeSheetHolder.getSheet();
        for (int i = 0; i < col.size(); i++) {
            sheet.setColumnWidth(col.get(i),width);
        }
    }
}
