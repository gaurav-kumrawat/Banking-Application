package com.psl.Banking.DynamoDb.Repository;

import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.psl.Banking.DynamoDb.config.DynamoDbConfig;
import com.psl.Banking.DynamoDb.constant.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class DynamoDBRepo
{
  @Autowired
  DynamoDbConfig dynamoDbConfig;

  public void  createProductTable() throws Exception
  {
    try {
      System.out.println("Creating the table, wait...");
      Table table =dynamoDbConfig.getDynamoDB().createTable (AppConstant.PRODUCT,
          Arrays.asList (
              new KeySchemaElement("id", KeyType.HASH), // the partition key
              // the sort key
              new KeySchemaElement("name", KeyType.RANGE)
          ),
          Arrays.asList (
              new AttributeDefinition("id", ScalarAttributeType.S),
              new AttributeDefinition("name", ScalarAttributeType.S)
          ),
          new ProvisionedThroughput(10L, 10L)
      );
      table.waitForActive();
      System.out.println("Table created successfully.  Status: " +
          table.getDescription().getTableStatus());

    } catch (Exception e) {
      System.err.println("Cannot create the table: ");
      throw new Exception("Error has been occured");
    }
  }

  public Table getTable(String tableName) {
    Table table =dynamoDbConfig.getDynamoDB().getTable(tableName);
    return table;
  }

  public void deleteTable(String tableName ) throws Exception
  {
    Table table =dynamoDbConfig.getDynamoDB().getTable(tableName);
    try {
      System.out.println("Attempting to delete table; please wait...");
      table.delete();
      table.waitForDelete();
      System.out.print("Success.");

    }
    catch (Exception e) {
      System.err.println("Unable to delete table: ");
      System.err.println(e.getMessage());
      throw new Exception("Error has been occured");
    }
  }
}
