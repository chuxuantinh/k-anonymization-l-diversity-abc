package org.anonymization.examples;

import org.anonymization.repository.MongoDBService;
import org.deidentifier.arx.ARXResult;

import java.io.IOException;

import static org.anonymization.examples.Example.*;

public class ExampleMongo {

    final private static String connString = "mongodb://127.0.0.1:27017/Test-tinh";
    final private static String aggrQuery = getExampleQuery();
    final private static String collectionName = "privacy";

    public static void main(String[] args) throws IOException {
        MongoDBService service = new MongoDBService()
                .setConnectionStringURI(connString)
                .setCollectionName(collectionName)
                .setFields(fields)
                .setData(data)
                .setQueryString(aggrQuery);

        service.connect();

        service.executeQuery();

        ARXResult result = getResult();
        processResults(result);
    }

    private static String getExampleQuery() {
        return "{\n" +
                "  \"$project\":{\n" +
                "    \"name\":1,\n" +
                "    \"age\":1,\n" +
                "    \"disease\":1,\n" +
                "    \"nationality\":1,\n" +
                "    \"zip\":\"$address.zip\"\n" +
                "  }\n" +
                "}";
    }
}
