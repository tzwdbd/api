package com.oversea.api.mongo;

/**
 *  分表策略
 * @author fj
 *
 */
public interface ShardingStrategy{

	public String getTargetTableName(String tableName, Object arg);
}
