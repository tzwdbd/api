package com.oversea.api.util;

/**
 * 动态信息相关工具类
 * 
 * @author yaohb
 *
 */
public class FeedUtil {
	
	/**
	 * feed id -> String to Long
	 * @param feedId
	 * @return
	 */
	public static Long parseFeedId(String feedId){
		if(feedId.contains("_")){
			return Long.valueOf(feedId.split("_")[0]);
		}else{
			return Long.valueOf(feedId);
		}
	}
	/**
	 * 拼装feed id 和 timeline
	 * @param feedId
	 * @param timeline
	 * @return
	 */
	public static String concatFeedIdAndTimeline(Long feedId , Long timeline){
		StringBuffer ft = new StringBuffer();
		
		ft.append(feedId).append("_").append(timeline);
		
		return ft.toString();
	}
	/**
	 * 拼装feed id 和 timeline
	 * @param feedId
	 * @param timeline
	 * @return
	 */
	public static String concatFeedIdAndTimeline(String feedId , Long timeline){
		StringBuffer ft = new StringBuffer();
		
		ft.append(feedId).append("_").append(timeline);
		
		return ft.toString();
	}

}
