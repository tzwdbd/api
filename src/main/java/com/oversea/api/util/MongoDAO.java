package com.oversea.api.util;


import com.oversea.api.mongo.MongoShardTemplete;
import com.oversea.api.mongo.ShardingStrategy;
import com.oversea.api.mongo.shard.YYYYMMDateShardStrategy;

public class MongoDAO extends MongoShardTemplete<MongoBean> {

	@Override
	protected ShardingStrategy getShardingStrategy() {
		return new YYYYMMDateShardStrategy();
	}
}
