package com.oversea.api.enums;

/**
 * 下单任务操作类型
 * @author zoubf
 */
public enum ApiCode {
	
	CODE_SUCCESS("100", "成功"),
	CODE_FAIL("-1", "失败"),
	CODE_ERROR("-2", "异常"),
	
	CODE_WEB_SPIDER_ANALYSIS("201", "正在解析"),
	CODE_WEB_SPIDER_RETRY("203", "未查询到，请重试"),
	CODE_WEB_SPIDER_NO_RESULT("204", "无结果"),
	CODE_WEB_SPIDER_UNSUPPORT("205", "暂不支持的网站"),
	CODE_WEB_SPIDER_THIRD_PART("206", "第三方"),
	CODE_WEB_SPIDER_ADD_ON("207", "凑单"),
	CODE_WEB_SPIDER_OFF_SALE("208", "未上架"),
	;

	protected String code;
	protected String message;

	ApiCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
