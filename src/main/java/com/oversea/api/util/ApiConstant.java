package com.oversea.api.util;

import java.util.HashSet;
import java.util.Set;

public class ApiConstant {
	
	//目前mongo测试环境开放mongo
	public static final String MONGO_ENV_TEST="test";
	
	public static final String METHOD_GET_PIC_SUFFIX = "ht.path.getPicSuffix";
	public static final String METHOD_UPLOAD_PHOTO = "ht.path.uploadPhoto";
	public static final String METHOD_SEARCH = "ht.seattle.seattleSearchV6";
	
	public static final String METHOD_CRAZY_SALE = "ht.path.crazySale";
	public static final String METHOD_FOX_FIND_COUPON = "ht.path.foxFindCoupon";
	
	public static final String METHOD_SETTLE_ACCOUNTS = "ht.seattle.settleAccounts";
	public static final String METHOD_PAYMENT2 = "ht.seattle.payment2";
	public static final String METHOD_PAYMENT = "ht.seattle.payment";
	public static final String METHOD_PAYMENT_WAP = "ht.seattle.paymentWap";
	public static final String METHOD_GO_APP_PAY = "ht.path.goAppPay";
	
	public static final String METHOD_GROUP_CASHIER = "ht.ginza.groupCashier";
	public static final String METHOD_GROUP_PAY = "ht.ginza.groupPay";
	public static final String METHOD_GROUP_WAP_PAY_SIGN = "ht.path.groupWapPaySign";
	public static final String METHOD_GROUP_PAY_SIGN = "ht.path.groupPaySign";
	
	public static final String METHOD_TROLLEYS = "ht.seattle.trolleys";
	public static final String METHOD_REMOVE_FROM_SHOPPING_CART= "ht.ginza.removeFromShoppingCart";
	public static final String METHOD_SELECT_TROLLEY = "ht.seattle.selectTrolley";
	public static final String METHOD_SYNC_TROLLEY = "ht.seattle.syncTrolley";
	
	public static final String METHOD_RECEIVE_COUPON = "ht.path.receiveCoupon";
	
	public static final String METHOD_MASTER_EXTEND_BIND = "ht.path.masterExtendBind";
	public static final String METHOD_GET_GROUP_GOODS_BY_GOODS_ID = "ht.ginza.getGroupGoodsByGoodsId";
	
	public static final String METHOD_SIGN_IN_DETAILS = "ht.seattle.signInDetails";
	public static final String METHOD_SIGN_IN = "ht.seattle.signIn";
	public static final String METHOD_CREDIT_EXCHANGE = "ht.seattle.creditExchange";
	public static final String METHOD_SHARE_SUCCESS = "ht.seattle.shareSuccess";
	public static final String METHOD_DOWN_APP_SAVE_URL = "ht.seattle.downAppSaveUrl";
	public static final String METHOD_MORE_CONVERSION = "ht.path.moreConversion";
	public static final String METHOD_CREDIT_GOODS_DETAIL = "ht.path.creditGoodsDetail";
	public static final String METHOD_CREDIT_SETTLE = "ht.path.creditSettle";
	public static final String METHOD_CREDIT_PAYMENT = "ht.path.creditPayment";
	
	public static final String METHOD_GET_GROUP_GOODS_ORDER_BY_ORDER_ID = "ht.ginza.getGroupGoodsOrderByOrderId";

	public static final String SYS_ERROR_ALERT = "SYS_ERROR_ALERT";
	public static final String SYS_ERROR_MONGO_ALERT = "SYS_ERROR_MONGO_ALERT";


	public static final Set<String> clientBlackSet = new HashSet<String>() {
		private static final long serialVersionUID = -5635290572188267290L;
		{
			add(METHOD_GET_PIC_SUFFIX);
			add(METHOD_CRAZY_SALE);
			add(METHOD_FOX_FIND_COUPON);
		}
	};
	
	public static final Set<String> h5WhiteSet = new HashSet<String>() {
		private static final long serialVersionUID = -7411728383793329737L;
		{
			add(METHOD_SETTLE_ACCOUNTS);
			add(METHOD_PAYMENT2);
			add(METHOD_PAYMENT);
			add(METHOD_PAYMENT_WAP);
			add(METHOD_GO_APP_PAY);
			
			add(METHOD_GROUP_CASHIER);
			add(METHOD_GROUP_PAY);
			add(METHOD_GROUP_WAP_PAY_SIGN);
			add(METHOD_GROUP_PAY_SIGN);
			
			add(METHOD_TROLLEYS);
			add(METHOD_REMOVE_FROM_SHOPPING_CART);
			add(METHOD_SELECT_TROLLEY);
			add(METHOD_SYNC_TROLLEY);
			
			add(METHOD_RECEIVE_COUPON);
			
			add(METHOD_MASTER_EXTEND_BIND);
			add(METHOD_GET_GROUP_GOODS_BY_GOODS_ID);
			
			add(METHOD_SIGN_IN_DETAILS);
			add(METHOD_SIGN_IN);
			add(METHOD_CREDIT_EXCHANGE);
			add(METHOD_SHARE_SUCCESS);
			add(METHOD_DOWN_APP_SAVE_URL);
			add(METHOD_MORE_CONVERSION);
			add(METHOD_CREDIT_GOODS_DETAIL);
			add(METHOD_CREDIT_SETTLE);
			add(METHOD_CREDIT_PAYMENT);
			
			add(METHOD_GET_GROUP_GOODS_ORDER_BY_ORDER_ID);
		}
	};
}
