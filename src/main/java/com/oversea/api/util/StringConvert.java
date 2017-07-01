package com.oversea.api.util;



public class StringConvert {
	
	public static String toRecordComponentDays(int days) {
		return days + "天";
	}
	
	public static String toRecordComponentPrice(int price) {
		return "￥" + price;
	}
	
//	public static String toRecordComponentStatus(int status) {
//		String result = null;
//		if(status == RingStatus.WaitingForSellerSending.getValue()){
//			result = "待处理";
//		}else if(status == RingStatus.SellerHasSended.getValue()){
//			result = "已处理";
//		}else{
//			result = "(请技术人员处理代码)";
//		}
//		return result;
//	}

}
