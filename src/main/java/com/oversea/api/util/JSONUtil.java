package com.oversea.api.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class JSONUtil {

    public static String string2json(String key, String value) { 
        JSONObject object = new JSONObject(); 
        object.put(key, value); 
        return object.toString(); 
    }

    @SuppressWarnings("rawtypes")
	public static Object json2Array(String json, Class valueClz) { 
        JSONArray jsonArray = JSONArray.fromObject(json); 
        return JSONArray.toArray(jsonArray, valueClz); 
    }

    public static String array2json(Object object) { 
        JSONArray jsonArray = JSONArray.fromObject(object); 
        return jsonArray.toString(); 
    }

    public static String map2json(Object object) { 
        JSONObject jsonObject = JSONObject.fromObject(object); 
        return jsonObject.toString(); 
    }

    @SuppressWarnings({"unchecked" })
	public static Map<Object,Object> json2Map(String json) { 
        JSONObject jsonObject = JSONObject.fromObject(json); 
        Map<Object, Object> valueMap = new HashMap<Object, Object>();
        for(Iterator<Object> iterator = jsonObject.keys(); iterator.hasNext();){
        	Object key = iterator.next();
        	Object value = jsonObject.get(key);
        	valueMap.put(key, value);
        }
        return valueMap;
     }

    public static String bean2json(Object object) { 
        JSONObject jsonObject = JSONObject.fromObject(object); 
        return jsonObject.toString(); 
    }

    public static String bean2jsonArray(Object object) { 
        JSONArray jsonObject = JSONArray.fromObject(object); 
        return jsonObject.toString(); 
    }

    @SuppressWarnings("rawtypes")
	public static Object json2Object(String json, Class beanClz) { 
        return JSONObject.toBean(JSONObject.fromObject(json), beanClz); 
    }

    public static String json2String(String json, String key) { 
        JSONObject jsonObject = JSONObject.fromObject(json); 
        return jsonObject.get(key).toString(); 
    } 
    
}
