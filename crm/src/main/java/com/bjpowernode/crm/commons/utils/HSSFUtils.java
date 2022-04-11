package com.bjpowernode.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * excel文件操作工具类
 * */
public class HSSFUtils {
    public static String getCellValueForStr(HSSFCell cell){
        String ret="";
        switch (cell.getCellType()){
            case STRING :
                ret = cell.getStringCellValue();
                break;
            case NUMERIC:
                ret = cell.getNumericCellValue()+"";
                break;
            case BOOLEAN:
                ret = cell.getBooleanCellValue()+"";
                break;
            case FORMULA:
                ret = cell.getCellFormula();
                break;
            default:
                break;
        }
        return ret;
    }
}
