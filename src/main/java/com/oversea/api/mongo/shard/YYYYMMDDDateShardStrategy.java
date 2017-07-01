package com.oversea.api.mongo.shard;

import com.oversea.api.mongo.ShardingStrategy;
import com.oversea.common.util.DateUtil;

import java.util.Date;

public class YYYYMMDDDateShardStrategy implements ShardingStrategy {

	@Override
	public String getTargetTableName(String tableName, Object arg) {
		StringBuffer sb = new StringBuffer(tableName);
		if(arg instanceof Date){
			Date date = (Date)arg;
			sb.append("_").append(DateUtil.format(date, "yyyyMMdd"));
		}
		return sb.toString();
	}

}
