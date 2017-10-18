package com.oversea.api.mongo;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoWriter;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.oversea.api.mongo.shard.YYYYMMDDDateShardStrategy;
import com.oversea.common.enums.SmsPriority;
import com.oversea.common.manager.ResourcesManager;
import com.oversea.common.util.EmaySmsSendUtil;

public class ApiMongoTemplete extends MongoTemplate {
	
	private static Log logger = LogFactory.getLog(ApiMongoTemplete.class);
	
	@Resource
	private ResourcesManager resourcesManager;
	/**
	 * Constructor used for a basic template configuration
	 * 
	 * @param mongo must not be {@literal null}.
	 * @param databaseName must not be {@literal null} or empty.
	 */
	public ApiMongoTemplete(Mongo mongo, String databaseName) {
		super(new SimpleMongoDbFactory(mongo, databaseName), null);
	}

	/**
	 * Constructor used for a template configuration with user credentials in the form of
	 * {@link org.springframework.data.authentication.UserCredentials}
	 * 
	 * @param mongo must not be {@literal null}.
	 * @param databaseName must not be {@literal null} or empty.
	 * @param userCredentials
	 */
	public ApiMongoTemplete(Mongo mongo, String databaseName, UserCredentials userCredentials) {
		super(new SimpleMongoDbFactory(mongo, databaseName, userCredentials));
	}

	/**
	 * Constructor used for a basic template configuration.
	 * 
	 * @param mongoDbFactory must not be {@literal null}.
	 */
	public ApiMongoTemplete(MongoDbFactory mongoDbFactory) {
		super(mongoDbFactory, null);
	}

	
	public void insertAll(Collection<? extends Object> objectsToSave) {
		
		doInsertAll(objectsToSave, super.getConverter());
	}

	protected <T> void doInsertAll(Collection<? extends T> listToSave, MongoWriter<T> writer) {
		Map<String, List<T>> objs = new HashMap<String, List<T>>();
		String collectionName = "";
		for (T o : listToSave) {
			Annotation[] as=o.getClass().getAnnotations();
			for(Annotation ann:as){
				if(Document.class.equals(ann.annotationType())){                      
					Document document=o.getClass().getAnnotation(Document.class);
					collectionName = document.collection();
					break;
				}
			}
			String collection = new YYYYMMDDDateShardStrategy().getTargetTableName(collectionName,new Date());
			List<DBObject> dbDocList = getDb().getCollection(collection).getIndexInfo();
			if(dbDocList.size()<=1){
				BasicDBObject dbDoc = new BasicDBObject();
				dbDoc.put("method", 1);
				getDb().getCollection(collection).createIndex(dbDoc);
				BasicDBObject dbDoc1 = new BasicDBObject();
				dbDoc1.put("requestBaseParams.user_id", 1);
				getDb().getCollection(collection).createIndex(dbDoc1);
			}
			List<T> objList = objs.get(collection);
			if (null == objList) {
				objList = new ArrayList<T>();
				objs.put(collection, objList);
			}
			objList.add(o);

		}

		for (Map.Entry<String, List<T>> entry : objs.entrySet()) {
			doInsertBatch(entry.getKey(), entry.getValue(), super.getConverter());
		}
	}
	
	//启动的时候查看mongodb状态是否正常
	public void init(){
		try {
			getDb();		
		} catch (Exception e) {
			logger.error("mongodb启动异常~", e);
			EmaySmsSendUtil.sendNoticeSms("13858109986,18606521817", "mongodb启动异常",
					SmsPriority.ERROR.getValue(), 0L);
		}
	}
}
