package com.base.mongodb;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongodbUtil {
    
    private static MongoClient mongoClient = null;
    
    @Before
    public void before() {
        mongoClient = new MongoClient("101.200.129.112", 27017);
    }
    
    @Test
    public void createDataBase() {
        
    }
    
    @Test
    public void add() {
        MongoDatabase db = mongoClient.getDatabase("dntest");
        MongoCollection<Document> cons = db.getCollection("mycon1");
        
        Document document = new Document();
        document.append("name", "jack");
        document.append("sex", "男");
        document.append("age", "22");
        document.append("shu", "shu1");
        document.append("lastaccess", System.currentTimeMillis());
        
        cons.insertOne(document);
    }
    
    @Test
    public void del() {
        MongoDatabase db = mongoClient.getDatabase("dntest");
        MongoCollection<Document> cons = db.getCollection("mycon1");
        
        BasicDBObject bdb = new BasicDBObject();
        bdb.put("name", "java");
        cons.deleteOne(bdb);
    }
    
    @Test
    public void update() {
        MongoDatabase db = mongoClient.getDatabase("dntest");
        MongoCollection<Document> cons = db.getCollection("mycon1");
        
        BasicDBObject condition = new BasicDBObject();
        condition.put("name", "java");
        
        BasicDBObject set = new BasicDBObject();
        set.put("name", "jack");
        
        BasicDBObject set1 = new BasicDBObject();
        set1.put("$set", set);
        cons.updateOne(condition, set1);
    }
    
    @Test
    public void find() {
        MongoDatabase db = mongoClient.getDatabase("dntest");
        MongoCollection<Document> cons = db.getCollection("mycon1");
        
        FindIterable<Document> fi = cons.find();
        MongoCursor<Document> mc = fi.iterator();
        while (mc.hasNext()) {
            System.out.println(mc.next());
        }
        
        //        BasicDBObject condition = new BasicDBObject();
        //        condition.put("$lte", 18);
        //        
        //        BasicDBObject condition1 = new BasicDBObject();
        //        condition1.put("age", condition);
        //        FindIterable<Document> fi1 = cons.find(condition1);
        //        MongoCursor<Document> mc1 = fi1.iterator();
    }
}
