package com.cube.vn.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

/**
 * テストユーティリティー.
 */
public class TestUtil {
	/**
	 * 文字作成
	 * @param lengthText 長さ
	 * @param isJapanese 文字が日本語か判断するフラグ
	 * @return 文字
	 */
	public static String createText(int lengthText, boolean isJapanese) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lengthText; i++) {
			if (isJapanese) {
				sb.append("あ");
			} else {
				sb.append("a");
			}
		}
		return sb.toString();
	}
	
	/**
	 * クラスアノテーション取得.
	 * @param c クラス
	 * @param annotation アノテーション対象
	 * @return クラスアノテーション
	 */
	public static <T extends Annotation> T getClassAnnotation(Class<?> c, Class<T> annotation) {
		return (T) c.getAnnotation(annotation);
	}
	
	/**
	 * フィールドアノテーション.
	 * @param c クラス
	 * @param fieldName フィールド名
	 * @param annotation アノテーション対象
	 * @return フィールドアノテーション取得
	 */
	public static <T extends Annotation> T getFieldAnnotation(Class<?> c, String fieldName, Class<T> annotation) {
	    try {
	        Field f = c.getDeclaredField(fieldName);
	        return (T)f.getAnnotation(annotation);
	    } catch (NoSuchFieldException ex) {
	        throw new RuntimeException(ex);
	    }
    }
	
	/**
	 * メソッドアノテーション.
	 * @param c クラス
	 * @param methodName メソッド名
	 * @param annotation アノテーション対象
	 * @return メソッドアノテーション取得
	 */
	public static <T extends Annotation> T getMethodAnnotation(
		Class<?> c, String methodName, Class<T> annotation) {
	    try {
	        Method m = c.getDeclaredMethod(methodName);
	        return (T)m.getAnnotation(annotation);
	    } catch (NoSuchMethodException ex) {
	        throw new RuntimeException(ex);
	    }
	}
	
	/**
	 * フィールドのデータ種類のチェック.
	 * @param c クラス
	 * @param fieldName フィールド名
	 * @param fieldNameDataType フィールドのデータ種類の対象
	 * @return true:OK/false:NG
	 */
	public static boolean isDataType(Class<?> c, String fieldName, Class<?> fieldNameDataType) {
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields) {
			if(f.getName().equals(fieldName) && f.getType().equals(fieldNameDataType)){
	            return true;
	        }
		}
		return false;
	}
	
	/**
	 *    日付取得.
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 時
	 * @param minute 分
	 * @param second 秒
	 * @return 日付
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
    	Calendar calendar = Calendar.getInstance();
    	int monthOfCalendar = 0;
    	switch (month) {
    	case 1:
    		monthOfCalendar = Calendar.JANUARY;
    		break;
    	case 2:
    		monthOfCalendar = Calendar.FEBRUARY;
    		break;
    	case 3:
    		monthOfCalendar = Calendar.MARCH;
    		break;
    	case 4:
    		monthOfCalendar = Calendar.APRIL;
    		break;
    	case 5:
    		monthOfCalendar = Calendar.MAY;
    		break;
    	case 6:
    		monthOfCalendar = Calendar.JUNE;
    		break;
    	case 7:
    		monthOfCalendar = Calendar.JULY;
    		break;
    	case 8:
    		monthOfCalendar = Calendar.AUGUST;
    		break;
    	case 9:
    		monthOfCalendar = Calendar.SEPTEMBER;
    		break;
    	case 10:
    		monthOfCalendar = Calendar.OCTOBER;
    		break;
    	case 11:
    		monthOfCalendar = Calendar.NOVEMBER;
    		break;
    	case 12:
    		monthOfCalendar = Calendar.DECEMBER;
    		break;
    	}
    	calendar.set(year, monthOfCalendar, day, hour, minute, second);
		return calendar.getTime();
	}
}
