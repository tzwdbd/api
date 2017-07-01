package com.oversea.api.util;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mongoBean")
public class MongoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1760736259408887197L;
	
	private String name;
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "MongoBean [name=" + name + ", date=" + date + "]";
	}
	
	public static void main(String[] args) {
		Date date = new Date(1395888683L*1000);
		System.out.println(date);
	}
}
