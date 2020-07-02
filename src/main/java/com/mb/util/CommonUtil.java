package com.mb.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.mb.baseType.entity.BaseType;
import com.mb.listener.ResourceUtil;
import com.mb.menu.entity.BaseCombo;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 通用的一些方法
 */
public class CommonUtil {

    /**
     * 根据字节B转换成KB，MB或者GB
     *
     * @param size 字节B
     * @return
     */
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    /**
     * EXECL防止数字变成科学计数法的形式
     *
     * @param value
     * @return
     */
    public static String setExeclDate4Type(Cell cell) {
        String val = "";
        if (null != cell) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    val = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    Boolean val1 = cell.getBooleanCellValue();
                    val = val1.toString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    val = df.format(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return val;
    }

    /**
     * 根据类型获取数据字典的分类值
     *
     * @param code 数据字典code
     * @return
     */
    public static List<BaseCombo> setComboValue(String code) {
        List<BaseType> typeList = ResourceUtil.allTypes.get(code);
        List<BaseCombo> comboList = new ArrayList<>();
        BaseCombo combo = null;
        for (BaseType baseType : typeList) {
            combo = new BaseCombo();
            combo.setId(baseType.getTypeCode());
            combo.setText(baseType.getTypeName());
            comboList.add(combo);
        }
        return comboList;
    }

    /**
     * 根据数据字典code和字典分类code获取唯一的分类的名称
     *
     * @param typeGroupCode 数据字典code
     * @param typeCode      字典分类code
     * @return
     */
    public static String getTypeNameByTypeGroup(String typeGroupCode, String typeCode) {
        String typeName = null;
        List<BaseType> typeList = ResourceUtil.allTypes.get(typeGroupCode);
        for (BaseType baseType : typeList) {
            if (typeCode.equals(baseType.getTypeCode())) {
                typeName = baseType.getTypeName();
                break;
            }
        }
        return typeName;
    }

}