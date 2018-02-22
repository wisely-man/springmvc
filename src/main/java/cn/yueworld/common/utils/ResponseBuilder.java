package cn.yueworld.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ResponseBuilder
 * 2015-7-17-上午10:23:47
 * @version 1.0.0
 * 
 */
public class ResponseBuilder {

	private static String SUCCESS_CODE = "0";
	private static String ERROR_CODE = "-1";
	private static String SUCCESS_MSG = "成功";
	private static String ERROR_MSG = "失败，请联系管理员";

	public static Map<String, Object> build(String code, String msg, Object obj) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", code);
		retMap.put("msg", msg);
		retMap.put("data", obj);
		return retMap;
	}

	public static Map<String, Object> buildSuccess() {
		return build(SUCCESS_CODE, SUCCESS_MSG, "");
	}
	
	public static Map<String, Object> buildSuccess(String msg, Object obj) {
		return build(SUCCESS_CODE, msg, obj);
	}

	public static Map<String, Object> buildSuccess(Object obj) {
		return build(SUCCESS_CODE, SUCCESS_MSG, obj);
	}

	public static Map<String, Object> buildError() {
		return build(ERROR_CODE, ERROR_MSG, null);
	}

	public static Map<String, Object> buildError(Object obj) {
		return build(ERROR_CODE, ERROR_MSG, obj);
	}

	public static Map<String, Object> buildError(String msg, Object obj) {
		return build(ERROR_CODE, msg, obj);
	}

	public static Map<String, Object> buildError(String msg) {
		return build(ERROR_CODE, msg, null);
	}
	
}
