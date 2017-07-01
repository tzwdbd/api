package com.oversea.api.dao.impl;

import com.oversea.api.dao.RequestStatisticsRecordDAO;
import com.oversea.api.domain.RequestStatisticsRecord;
import com.oversea.api.mongo.MongoShardTemplete;
import com.oversea.api.mongo.ShardingStrategy;
import com.oversea.api.mongo.shard.YYYYMMDDDateShardStrategy;

import java.util.Date;
import java.util.List;

public class RequestStatisticsRecordDAOImpl extends MongoShardTemplete<RequestStatisticsRecord> implements RequestStatisticsRecordDAO {
	
	public void insert(RequestStatisticsRecord requestStatisticsRecord){
		super.insert(requestStatisticsRecord,new Date());
	}

	@Override
	protected ShardingStrategy getShardingStrategy() {
		return new YYYYMMDDDateShardStrategy();
	}
	@Override
	public void insertALL(List<RequestStatisticsRecord> requestBaseParamsList) {
		super.insertAll(requestBaseParamsList,new Date());
	}
}
