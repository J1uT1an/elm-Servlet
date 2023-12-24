package com.luxintong.elm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.util
 * @className: CommonUtil
 * @author: Lu Xintong
 * @description <p>CommonUtil</p>
 * @date: 2023-12-15 17:19
 * @version: 1.0
 */
public class CommonUtil {
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
}
