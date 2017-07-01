package com.oversea.api.mongo;

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

public abstract class MongoTemplete<T> {

	private Class<T> entityClass = null;
	
	private String original_collectionName = null;
	
	@SuppressWarnings("unchecked")
	public MongoTemplete() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Annotation[] as=entityClass.getAnnotations();
		for(Annotation ann:as){
			if(Document.class.equals(ann.annotationType())){                      
				Document document=entityClass.getAnnotation(Document.class);
				original_collectionName = document.collection();
			}
		}
	}

	@Resource
	private MongoOperations mongoTemplate;
    
	/**
	 * 新增
	 * 
	 * @param t
	 */
	public void insert(T t) {
		mongoTemplate.insert(t);
	}
	
	public void updateMulti(Query query,Update update){
	    mongoTemplate.updateMulti(query, update, original_collectionName);
	}
	
	/**
	 * 新增 
	 * 
	 * @param t
	 */
	public void insert(T t,String collectionSuffix) {
		mongoTemplate.insert(t);
	}

	/**
	 * 批量新增
	 * 
	 * @param list
	 */
	public void insertAll(List<T> list) {
		mongoTemplate.insertAll(list);
	}

	/**
	 * 根据ID取得对象
	 * 
	 * @param id
	 * @return T
	 */
	public T find(String id) {
		return mongoTemplate.findById(id, entityClass);
	}
	
	/**
	 *  保存对象，Id存在则更新，Id不存在则新增
	 * @param t
	 */
	public void save(T t) {
		mongoTemplate.save(t);
	}

	/**
	 * 使用Query完成复杂查询
	 * 
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<T> find(Query query, int start, int limit) {
		query.skip(start);
		query.limit(limit);
		return mongoTemplate.find(query, entityClass);
	}
	
	/**
	 * 使用Query完成复杂查询
	 * 
	 * @param query
	 * @return
	 */
	public List<T> find(Query query) {
		return mongoTemplate.find(query, entityClass);
	}

	/**
	 * 查询全部数据
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return mongoTemplate.findAll(entityClass);
	}

	/**
	 * 使用Query完成复杂查询计数
	 * 
	 * @param query
	 * @return
	 */
	public long count(Query query) {
		return mongoTemplate.count(query, entityClass);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void modify(T t) {
		this.mongoTemplate.save(t);
	}

	/**
	 * 根据Id删除数据
	 * 
	 * @param id
	 */
	public void delete(String id) {
		this.mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), entityClass);
	}

	/**
	 * 删除符合Query查询结果的数据
	 * 
	 * @param query
	 */
	public void delete(Query query) {
		this.mongoTemplate.remove(query, entityClass);
	}
	
	/** 
	 *  有数据则更新，没有数据则新入
	 * @param query
	 * @param update
	 */
	public void updateInser(Query query, Update update){
        this.mongoTemplate.upsert(query, update, entityClass);
    }
	
	/** 
	 *  更新数据
	 * @param query
	 * @param update
	 */
	public void updateFirst(Query query, Update update){
		this.mongoTemplate.updateFirst(query, update, entityClass);
	}
	
	public MongoOperations getMongoOperations(){
		return mongoTemplate;
	}
	
	/**
	 * group by
	 * @param criteria
	 * @param groupBy
	 * @return
	 */
	public GroupByResults<T> group(Criteria criteria, GroupBy groupBy){
		GroupByResults<T> result=mongoTemplate.group(criteria,original_collectionName, groupBy, entityClass);
		return result;
	}

	/** 
	 * 聚合函数
	 * @param typedAggregation
	 * @param param
	 * @return
	 */
	public AggregationResults<T> aggregate(Aggregation aggregation){
		return mongoTemplate.aggregate(aggregation, original_collectionName, entityClass);
	}
	
	/** 
	 * mapReduce函数
	 * @param typedAggregation
	 * @param param
	 * @return
	 */
	public void mapReduce(String mapFunction, String reduceFunction,String outputCollectionName,Criteria criteria){
		getMongoOperations().getCollection(original_collectionName).mapReduce(mapFunction, reduceFunction,outputCollectionName, criteria==null?null:criteria.getCriteriaObject());
	}
}
