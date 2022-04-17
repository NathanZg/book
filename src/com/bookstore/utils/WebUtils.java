package com.bookstore.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ClassName: WebUtils
 * Description:
 * date: 2022/1/29 10:35
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class WebUtils {
    /**
     * 把参数封装成beam对象
     * @param value
     * @param beam
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBeam(Map value, T beam){
        try {
            BeanUtils.populate(beam, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return beam;
    }

    /**
     * 判断str是不是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    /**
     * 把字符串传换成int类型数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        if(strInt==null||strInt==""){
            return defaultValue;
        }else if(isNumeric(strInt)){
            return Integer.parseInt(strInt);
        }else {
            return defaultValue;
        }
    }


}
