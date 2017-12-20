package com.oversea.api.execute;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;
import org.nlpcn.commons.lang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HbaseUtil.class);
	
	private String zkQuorum;
	private String zkPort;
	private String zkRetry;
	private String hbaseRetryNum;
	private String hbasePause;

	private static ExecutorService executor;
	private static Configuration config;
	private static Connection conn;
	
	private static ConcurrentHashMap<String, Table> tableMap = new ConcurrentHashMap<String, Table>();
	
	public void init() {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", zkQuorum);
		config.set("hbase.zookeeper.property.clientPort", zkPort);
		config.set("hbase.client.retries.number", hbaseRetryNum);
		config.set("hbase.client.pause", hbasePause);
		config.set("zookeeper.recovery.retry", zkRetry);
		
		executor = Executors.newFixedThreadPool(5);
		
		try {
			conn = ConnectionFactory.createConnection(config, executor);
			
			if(conn != null) {
				HBaseAdmin hBaseAdmin = (HBaseAdmin) conn.getAdmin();
				
				if(hBaseAdmin != null) {
					ClusterStatus clusterStatus = hBaseAdmin.getClusterStatus();
			        
			        if(clusterStatus != null) {
			        	ServerName master = clusterStatus.getMaster();
				        Collection<ServerName> servers = clusterStatus.getServers();
				        
				        logger.error("HbaseUtil Master host={}, port={} ", master.getHostname(), master.getPort());
				        
				        for(ServerName serverName : servers) {
				        	logger.error("HbaseUtil Region host={}, port={} ", serverName.getHostname(), serverName.getPort());
				        }
			        }
				}
			}
		} catch (Exception e) {
			logger.error("HbaseUtil_createConnection_error: ", e);
		}
		
		logger.error("HbaseUtil init");
	}
	
	public void close() {
		try {
			for(String tableName : tableMap.keySet()) {
				Table table = tableMap.get(tableName);
				
				if(table != null) {
					table.close();
				}
			}
			
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("HbaseUtil_close_error: ", e);
		}
	
		logger.error("HbaseUtil close");
	}
	
	public synchronized Table getTable(String tableName) {
		if(StringUtil.isBlank(tableName)) {
			logger.error("HbaseUtil tableName is null");
			return null;
		}
		
		try {
			Table table = tableMap.get(tableName);
			
			if(table == null) {
				if(conn == null || conn.isClosed() || conn.isAborted()) {
					logger.error("HbaseUtil recreate conn, tableName={}", tableName);
					conn = ConnectionFactory.createConnection(config, executor);
				}
				
				if(conn == null || conn.isClosed() || conn.isAborted()) {
					logger.error("HbaseUtil recreate conn fail, tableName={}", tableName);
					return null;
				}
				
				table = conn.getTable(TableName.valueOf(tableName));
				
				if(table != null) {
					tableMap.put(tableName, table);
				}
			}
			
			return table;
		} catch (Exception e) {
			logger.error("HbaseUtil getTable error: ", e);
			return null;
		}
	}
	
	public String getZkQuorum() {
		return zkQuorum;
	}

	public void setZkQuorum(String zkQuorum) {
		this.zkQuorum = zkQuorum;
	}

	public String getZkPort() {
		return zkPort;
	}

	public void setZkPort(String zkPort) {
		this.zkPort = zkPort;
	}

	public String getZkRetry() {
		return zkRetry;
	}

	public void setZkRetry(String zkRetry) {
		this.zkRetry = zkRetry;
	}

	public String getHbaseRetryNum() {
		return hbaseRetryNum;
	}

	public void setHbaseRetryNum(String hbaseRetryNum) {
		this.hbaseRetryNum = hbaseRetryNum;
	}

	public String getHbasePause() {
		return hbasePause;
	}

	public void setHbasePause(String hbasePause) {
		this.hbasePause = hbasePause;
	}
}