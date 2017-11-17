//package com.github.hackyouroffice.foodle.storage;
//
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//import com.amazonaws.services.dynamodbv2.model.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static org.junit.Assert.*;
//
//public class LocationsDynamoDbClientIntegrationTest {
//
//    LocationsDynamoDbClient locationsDynamoDbClient;
//
//    @Before
//    public void setup() {
//        AmazonDynamoDBClient dynamoDbClient = new AmazonDynamoDBClient(new BasicAWSCredentials("TestAccessKey","TestSecretKey"));
//        dynamoDbClient.setEndpoint("http://localhost:8000");
//        locationsDynamoDbClient = new LocationsDynamoDbClient(dynamoDbClient);
//    }
//
//    @Test
//    public void foo() {
//        final Stream<ProposedLocationDataItem> all = locationsDynamoDbClient.findAll();
//    }
//
//
//    @Test
//    public void createTableTest() {
//        AmazonDynamoDB ddb = DynamoDBEmbedded.create().amazonDynamoDB();
//        try {
//            String tableName = "Movies";
//            String hashKeyName = "film_id";
//            CreateTableResult res = createTable(ddb, tableName, hashKeyName);
//
//            TableDescription tableDesc = res.getTableDescription();
//            assertEquals(tableName, tableDesc.getTableName());
//            assertEquals("[{AttributeName: " + hashKeyName + ",KeyType: HASH}]", tableDesc.getKeySchema().toString());
//            assertEquals("[{AttributeName: " + hashKeyName + ",AttributeType: S}]",
//                tableDesc.getAttributeDefinitions().toString());
//            assertEquals(Long.valueOf(1000L), tableDesc.getProvisionedThroughput().getReadCapacityUnits());
//            assertEquals(Long.valueOf(1000L), tableDesc.getProvisionedThroughput().getWriteCapacityUnits());
//            assertEquals("ACTIVE", tableDesc.getTableStatus());
//            assertEquals("arn:aws:dynamodb:ddblocal:000000000000:table/Movies", tableDesc.getTableArn());
//
//            ListTablesResult tables = ddb.listTables();
//            assertEquals(1, tables.getTableNames().size());
//        } finally {
//            ddb.shutdown();
//        }
//    }
//
//    private static CreateTableResult createTable(AmazonDynamoDB ddb, String tableName, String hashKeyName) {
//        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
//        attributeDefinitions.add(new AttributeDefinition(hashKeyName, ScalarAttributeType.S));
//
//        List<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
//        ks.add(new KeySchemaElement(hashKeyName, KeyType.HASH));
//
//        ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1000L, 1000L);
//
//        CreateTableRequest request =
//            new CreateTableRequest()
//                .withTableName(tableName)
//                .withAttributeDefinitions(attributeDefinitions)
//                .withKeySchema(ks)
//                .withProvisionedThroughput(provisionedthroughput);
//
//        return ddb.createTable(request);
//    }
//}
