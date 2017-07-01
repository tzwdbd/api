package com.oversea.api.dao;


import java.util.List;

import com.oversea.api.domain.RequestStatisticsRecord;

public interface RequestStatisticsRecordDAO {

	public void insert(RequestStatisticsRecord requestBaseParams);
	
	public void insertALL(List<RequestStatisticsRecord> requestBaseParamsList);
}
