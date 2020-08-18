package com.example.cms.service.utils;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.cms.entity.ExcelBean;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

/**
 * @author
 * @date 2018/12/13
 */

public class ExcelUtils {
    private final static String EXCEL_2003_L =".xls";
    private final static String EXCEL_2007_U =".xlsx";

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public  List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception{
        List<List<Object>> list = null;


        Workbook work = this.getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();

        int lastCellNum = 0;
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}


            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}

                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                // 比较当前行的列数跟表的最大的列数
                if (j == sheet.getFirstRowNum()) {
                    // 将第一行的列数设为最大
                    lastCellNum = row.getLastCellNum();
                }else {
                    lastCellNum = lastCellNum > row.getLastCellNum() ? lastCellNum : row.getLastCellNum();
                }
                for (int y = row.getFirstCellNum(); y < lastCellNum; y++) {
                    cell = row.getCell(y);
                    li.add(this.getValue(cell));
                }
                list.add(li);
            }
        }

        return list;

    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(EXCEL_2003_L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);
        }else if(EXCEL_2007_U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */

    public  String getValue(Cell cell) {
        String value = "";
        if(null==cell){
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    // 根据自己的实际情况，excel表中的时间格式是yyyy-MM-dd HH:mm:ss还是yyyy-MM-dd，或者其他类型
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 由于方法的返回值类型为String，这里将Date类型转为String，便于统一返回数据
                    value = format.format(date);;
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                    BigInteger num=new BigInteger("0");
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if(null!=value&&!"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if(1<item.length&&num.equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;

            case Cell.CELL_TYPE_FORMULA:

                value = String.valueOf(cell.getNumericCellValue());
                String str="NaN";
                if (value.equals(str)) {
                    value = cell.getStringCellValue().toString();
                }
                break;

            case Cell.CELL_TYPE_BOOLEAN:
                value = " "+ cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        String str="null";
        if(str.endsWith(value.trim())){
            value="";
        }
        return value;
    }





        /**
         * 导出Excel表
         * @param clazz 数据源model类型
         * @param objs excel标题以及对应的model字段
         * @param map 标题行数以及cell字体样式
         * @param sheetName 工作簿名称
         * @return
         * @throws IntrospectionException
         * @throws InvocationTargetException
         * @throws IllegalArgumentException
         * @throws IllegalAccessException
         */
        public static XSSFWorkbook createExcelFile(
                Class<?> clazz,
                List objs,
                Map<Integer,List<ExcelBean>> map,
                String sheetName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet(sheetName);

            createFont(workbook);
            createTableHeader(sheet, map);
            createTableRows(sheet, map, objs, clazz);
            return workbook;

        }
        private static XSSFCellStyle fontStyle;
        private static XSSFCellStyle fontStyle2;
        private static void createFont(XSSFWorkbook workbook) {
            //表头
            fontStyle = workbook.createCellStyle();
            XSSFFont font1 = workbook.createFont();
            font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font1.setFontName("黑体");
            font1.setFontHeightInPoints((short) 12);
            fontStyle.setFont(font1);
            fontStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            //内容
            fontStyle2 = workbook.createCellStyle();
            XSSFFont font2 = workbook.createFont();
            font2.setFontName("宋体");
            font2.setFontHeightInPoints((short)10);
            fontStyle2.setFont(font2);
            fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);
            fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);
            fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        }
        /**
         * 根据ExcelMapping 生成列头(多行列头)
         * @param sheet 工作簿
         * @param map 每行每个单元格对应的列头信息
         */
        private static void createTableHeader(
                XSSFSheet sheet,
                Map<Integer, List<ExcelBean>> map) {
            int startIndex = 0;
            int endIndex = 0;
            for(Map.Entry<Integer,List<ExcelBean>> entry: map.entrySet()){
                XSSFRow row = sheet.createRow(entry.getKey());
                List<ExcelBean> excels = entry.getValue();
                for(int x=0;x<excels.size();x++){

                    if(excels.get(x).getCols()>1){
                        if(x==0){
                            endIndex += excels.get(x).getCols()-1;

                            sheet.addMergedRegion(new CellRangeAddress(0, 0, startIndex, endIndex));
                            startIndex += excels.get(x).getCols();
                        }else{
                            endIndex += excels.get(x).getCols();
                            sheet.addMergedRegion(new CellRangeAddress(0, 0, startIndex, endIndex));
                            startIndex += excels.get(x).getCols();
                        }
                        XSSFCell cell = row.createCell(startIndex-excels.get(x).getCols());

                        cell.setCellValue(excels.get(x).getHeadTextName());
                        if(excels.get(x).getCellStyle() != null){

                            cell.setCellStyle(excels.get(x).getCellStyle());
                        }
                        cell.setCellStyle(fontStyle);
                    }else{
                        XSSFCell cell = row.createCell(x);

                        cell.setCellValue(excels.get(x).getHeadTextName());
                        if(excels.get(x).getCellStyle() != null){

                            cell.setCellStyle(excels.get(x).getCellStyle());
                        }
                        cell.setCellStyle(fontStyle);
                    }
                }
            }
        }
        /**
         * 为excel表中循环添加数据
         * @param sheet
         * @param map  字段名
         * @param objs	查询的数据
         * @param clazz 无用
         */
        private static void createTableRows(
                XSSFSheet sheet,
                Map<Integer,List<ExcelBean>> map,
                List<Map<String,Object>> objs,
                Class<?> clazz)
                throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            int rowindex = map.size();
            int maxkey = 0;
            List<ExcelBean> ems = new ArrayList<>();
            for(Map.Entry<Integer,List<ExcelBean>> entry : map.entrySet()){
                if(entry.getKey() > maxkey){
                    maxkey = entry.getKey();
                }
            }
            ems = map.get(maxkey);
            List<Integer> widths = new ArrayList<Integer>(ems.size());
            for(Map<String,Object> obj : objs){
                XSSFRow row = sheet.createRow(rowindex);
                for(int i=0;i<ems.size();i++){
                    ExcelBean em = (ExcelBean)ems.get(i);
                    String propertyName = em.getPropertyName();
                    Object value = obj.get(propertyName);
                    XSSFCell cell = row.createCell(i);
                    String cellValue = "";
                    if("valid".equals(propertyName)){
                        cellValue = value.equals(1)?"启用":"禁用";
                    }else if(value==null){
                        cellValue = "";
                    }else if(value instanceof Date){
                        cellValue = new SimpleDateFormat("yyyy-MM-dd").format(value);
                    }else{
                        cellValue = value.toString();
                    }
                    cell.setCellValue(cellValue);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    cell.setCellStyle(fontStyle2);
                    sheet.autoSizeColumn(i);
                }
                rowindex++;
            }


            for(int index=0;index<widths.size();index++){
                Integer width = widths.get(index);
                width = width<2500?2500:width+300;
                width = width>10000?10000+300:width+300;
                sheet.setColumnWidth(index, width);
            }
        }
    }


