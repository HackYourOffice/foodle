package com.github.hackyouroffice.foodle.storage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.stream.Stream;

public class LocationsDynamoDbClient {

    private final AmazonDynamoDBClient dynamoDBClient;

    public LocationsDynamoDbClient(AmazonDynamoDBClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    /**
     * Loads an item from DynamoDB by primary Hash Key. Callers of this method should pass in an
     * object which represents an item in the DynamoDB table item with the primary key populated.
     *
     * @param tableItem
     * @return
     */
    public ProposedLocationDataItem loadItem(final ProposedLocationDataItem tableItem) {
        DynamoDBMapper mapper = createDynamoDBMapper();
        return mapper.load(tableItem);
    }


    public Stream<ProposedLocationDataItem> findAll() {
        DynamoDBMapper mapper = createDynamoDBMapper();
        return mapper.scan(ProposedLocationDataItem.class, new DynamoDBScanExpression()).stream();
    }


    /**
     * Stores an item to DynamoDB.
     *
     * @param tableItem
     */
    public void saveItem(final ProposedLocationDataItem tableItem) {
        DynamoDBMapper mapper = createDynamoDBMapper();
        mapper.save(tableItem);
    }

    /**
     * Creates a {@link DynamoDBMapper} using the default configurations.
     *
     * @return
     */
    private DynamoDBMapper createDynamoDBMapper() {
        return new DynamoDBMapper(dynamoDBClient);
    }
}
