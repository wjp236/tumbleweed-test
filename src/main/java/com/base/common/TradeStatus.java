package com.base.common;

public enum TradeStatus {

	/**
	 * 1-等待付款
	 */
	WAITPAY("1", "等待付款"),
	/**
	 * 2-等待发货
	 */
	WAITSEND("2", "等待发货"),
	/**
	 * 3-等待确认收货
	 */
	UNSURE("3", "等待确认收货"),
	/**
	 * 4-交易成功
	 */
	SUCCESS("4", "交易成功"),
	/**
	 * 5-交易失败
	 */
	FAILED("5", "交易失败"),
	/**
	 * 6-交易关闭
	 */
	CLOSE("6", "交易关闭"),
	/**
	 * 7-退款待处理
	 */
	WAITREFUND("7", "退款待处理"),
	/**
	 * 8-退款成功
	 */
	REFUNDSUCCESS("8", "退款成功"),
	/**
	 * 9-退款失败
	 */
	REFUNDFAILED("9", "退款失败"),
	DOING("A", "进行中"),
	WAITWITHDRAW("B", "等待提现");

	private final String code;
	private final String text;

	TradeStatus(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String code() {
		return this.code;
	}

	public String getText() {
		return text;
	}

	public static TradeStatus codeOf(String code) {
		for (TradeStatus val : TradeStatus.values()) {
			if (val.code().equals(code)) {
				return val;
			}
		}

		return null;
	}

}
