package com.oversea.api.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *  制定分表查询mongo
 * @author fj
 * @param <T>
 */
public  abstract class MongoShardTemplete<T> {

	private Class<T> entityClass = null;
	
	@Resource
	private MongoOperations mongoReqTemplate;
	
	private String original_collectionName = null;
	
	protected abstract ShardingStrategy getShardingStrategy();

	@SuppressWarnings("unchecked")
	public MongoShardTemplete() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Annotation[] as=entityClass.getAnnotations();
		for(Annotation ann:as){
			if(Document.class.equals(ann.annotationType())){                      
				Document document=entityClass.getAnnotation(Document.class);
				original_collectionName = document.collection();
			}
		}
	}
    
	/**
	 * 新增
	 * 
	 * @param t
	 */
	public void insert(T t,Object param) {
		mongoReqTemplate.insert(t,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}
	
	
	/**
	 * 批量新增
	 * 
	 * @param list
	 */
	public void insertAll(List<T> list,Object param) {
		mongoReqTemplate.insertAll(list);
		/*if(list==null){
			return;
		}
		for(T t : list){
			mongoReqTemplate.insert(t,getShardingStrategy().getTargetTableName(original_collectionName,param));
		}*/
	}

	/**
	 * 根据ID取得对象
	 * 
	 * @param id
	 * @return T
	 */
	public T find(String id,Object param) {
		return mongoReqTemplate.findById(id, entityClass,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}

	/**
	 * 使用Query完成复杂查询
	 * 
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<T> find(Query query, int start, int limit,Object param) {
		query.skip(start);
		query.limit(limit);
		return mongoReqTemplate.find(query, entityClass,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}

	/**
	 * 查询全部数据
	 * 
	 * @return
	 */
	public List<T> findAll(Object param) {
		return mongoReqTemplate.findAll(entityClass,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}
	
	/**
	 * 使用Query完成复杂查询
	 * 
	 * @param query
	 * @return
	 */
	public List<T> find(Query query,Object param) {
		return mongoReqTemplate.find(query, entityClass,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}

	/**
	 * 使用Query完成复杂查询计数
	 * 
	 * @param query
	 * @return
	 */
	public long count(Query query,Object param) {
		return mongoReqTemplate.count(query,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void modify(T t,Object param) {
		this.mongoReqTemplate.save(t,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}
	
	/** 
	 *  更新一条
	 * @param query 查询对象
	 * @param update 更新对象
	 * @param param 分表字段
	 */
	public void updateFirst(Query query,Update update,Object param){
		this.mongoReqTemplate.updateFirst(query, update, getShardingStrategy().getTargetTableName(original_collectionName,param));
	}
	
	/** 
	 *  更新多条
	 * @param query 查询对象
	 * @param update 更新对象
	 * @param param 分表字段
	 */
	public void updateMulti(Query query,Update update,Object param){
		this.mongoReqTemplate.updateMulti(query, update, getShardingStrategy().getTargetTableName(original_collectionName,param));
	}

	/**
	 * 根据Id删除数据
	 * 
	 * @param id
	 */
	public void delete(String id,Object param) {
		this.mongoReqTemplate.remove(new Query(Criteria.where("_id").is(id)), entityClass,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}

	/**
	 * 删除符合Query查询结果的数据
	 * 
	 * @param query
	 */
	public void delete(Query query,Object param) {
		this.mongoReqTemplate.remove(query, entityClass,getShardingStrategy().getTargetTableName(original_collectionName,param));
	}
	
	/**
	 * group by
	 * @param criteria
	 * @param groupBy
	 * @return
	 */
	public GroupByResults<T> group(Criteria criteria, GroupBy groupBy,Object param){
		GroupByResults<T> result=mongoReqTemplate.group(criteria,getShardingStrategy().getTargetTableName(original_collectionName,param), groupBy, entityClass);
		return result;
	}
	
	public MongoOperations getMongoOperations(){
		return mongoReqTemplate;
	}
	
	/** 
	 * 聚合函数
	 * @param aggregation
	 * @param param
	 * @return
	 */
	public AggregationResults<T> aggregate(Aggregation aggregation,Object param){
		return mongoReqTemplate.aggregate(aggregation, getShardingStrategy().getTargetTableName(original_collectionName,param), entityClass);
	}

    /**
     * mapReduce函数
     * @param mapFunction
     * @param reduceFunction
     * @param outputCollectionName
     * @param criteria
     * @param param
     */
	public void mapReduce(String mapFunction, String reduceFunction,String outputCollectionName,Criteria criteria,Object param){
		getMongoOperations().getCollection(getShardingStrategy().getTargetTableName(original_collectionName,param)).mapReduce(mapFunction, reduceFunction,outputCollectionName, criteria==null?null:criteria.getCriteriaObject());
	}
	

    /**
     * handel 方法
     * @param collectionName
     * @param query
     * @param dch
     */
	public void executeQuery(String collectionName,Query query, DocumentCallbackHandler dch){
	    DBCollection db=getMongoOperations().getCollection(collectionName);
	    DBCursor dbc = null;
	    try{
    	    dbc=db.find(query.getQueryObject(), new BasicDBObject());
    	    dbc.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
    	    dbc.batchSize(2000);
    	    while(dbc.hasNext()){
    	        DBObject dbobject = dbc.next();
    	        dch.processDocument(dbobject);
    	    }
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        if (dbc != null) {
	            dbc.close();
            }
	    }
	}
}
