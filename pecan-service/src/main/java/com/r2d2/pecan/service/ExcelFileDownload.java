package com.r2d2.pecan.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/7.
 */
public class ExcelFileDownload {

    public String fileName ;

    private String localFile = "E:/project/bboss-oms/oms-web/src/main/resources/config/COMMISSION_REPORT.xlsx";


    public void creatFile(){

        FileInputStream myXls =null;
        FileOutputStream fos =null ;
        XSSFWorkbook xssfWorkbook;
        XSSFSheet xssfSheet;

        try {
            myXls = new FileInputStream(localFile);
            xssfWorkbook = new XSSFWorkbook(myXls);
            xssfSheet =  xssfWorkbook.getSheetAt(0);
        //    xssfSheet.shiftRows(9, xssfSheet.getLastRowNum(),  1, true, false);

        //    Row row = sheet.getRow(13);


            SXSSFWorkbook sxssfWork = new SXSSFWorkbook(xssfWorkbook,1000);
           Sheet sheet = sxssfWork.getSheetAt(0);

            Row targetRow = xssfSheet.getRow(11);
         //   targetRow.getCell(1).setCellValue("VVV");
            Row newRow = sheet.createRow(17);

            if (targetRow != null) {
                System.out.println("not null");
                newRow.setHeight(targetRow.getHeight());
            }

            Cell newCell ;
            Cell targetCell;
            for (int m = 0; m < targetRow.getPhysicalNumberOfCells(); m++) {
                newCell = newRow.createCell(m);
                targetCell = targetRow.getCell(m);
                if (targetCell == null) {
                    targetCell = targetRow.createCell(m);
                }

                newCell.setCellStyle(targetCell.getCellStyle());
                newCell.setCellType(targetCell.getCellType());
            }

            fos = new FileOutputStream(fileName);
            sxssfWork.write(fos);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(myXls);
            IOUtils.closeQuietly(fos);
        }
    }

    public File initFile() throws Exception {
        fileName = "D://temp"+ "/" + "temp1" + ".xlsx" ;
        File file = new File(fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }


    /**
     * 合并单元格
     *
     * @return 返回结果
     */
    private Map<Integer, Integer> commissionMergeCell() {
        //excel合并单元格
        Map<Integer, Integer> collName = new HashMap<Integer, Integer>();
        collName.put(1, 2);
        return collName;
    }

    /**
     * Excel显示列字段信息
     *
     * @return 返回结果
     */
    private Map<Integer, String> commissionExcelCell() {

        //excel要显示的列号和对应的列名
        Map<Integer, String> collName = new HashMap();
        collName.put(0,"merchantNo");
        collName.put(1, "shortName");
        collName.put(2, "emptyColumn");
        collName.put(3, "receiveAmt");
        collName.put(4, "payAmt");
        collName.put(5, "capitalAmt");
        collName.put(6, "remark");

        return collName;
    }

    /**
     * 数值字段的列号
     *
     * @return 返回结果
     */
    private Set<Integer> commissionExcelNumCell() {
        //数值字段的列号
        Set<Integer> digitCol = new HashSet();
        digitCol.add(3);
        digitCol.add(4);
        digitCol.add(5);

        return digitCol;
    }

    public static void main(String args[]){
        ExcelFileDownload fileDown = new ExcelFileDownload();
        try {
            fileDown.initFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileDown.creatFile();
    }

}
