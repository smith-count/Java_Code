package Demo;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoUse{
   public static void main( String args[] ){
      try{   
       // 连接到 mongodb 服务
         MongoClient mongoClient = new MongoClient("localhost", 27017 );
       
         // 连接到数据库
         MongoDatabase mongoDatabase = mongoClient.getDatabase("DataExpr");  
       System.out.println("Connect to database successfully");
        //记录是文档
       MongoCollection<Document> collection=mongoDatabase.getCollection("student");
//       Document document = new Document();
//       Document document1 = new Document();
//       document1.put("English", 45);
//       document1.put("Math", 89);
//       document1.put("Computer", 100);
//       document.put("Name", "scofied");
//       document.put("score",document1);
       BasicDBObject whereQuery = new BasicDBObject();
       whereQuery.put("Name","scofied");
   		FindIterable<Document> cursor = collection.find(whereQuery);
   	
   	    System.out.println(cursor.first().toString());
   	
       
       

       //collection.insertOne(document);
      }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
      
      
      
   }
}