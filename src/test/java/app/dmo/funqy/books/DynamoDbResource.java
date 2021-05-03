package app.dmo.funqy.books;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

import java.net.URI;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class DynamoDbResource implements QuarkusTestResourceLifecycleManager {

    private static final int PORT = 8000;

    @SuppressWarnings("rawtypes")
    GenericContainer dynamodb;

    @Override
    public Map<String, String> start() {
        dynamodb = new GenericContainer<>("amazon/dynamodb-local")
                .withExposedPorts(PORT)
                .waitingFor(Wait.forLogMessage(".*Initializing DynamoDB Local with the following configuration.*", 1))
        ;
        dynamodb.start();

        String endpoint = String.format("http://%s:%s", dynamodb.getContainerIpAddress(), dynamodb.getMappedPort(PORT));
        System.out.println("DDB Endpoint: " + endpoint);
        initTable(endpoint);

        return Map.of("quarkus.dynamodb.endpoint-override", endpoint);
    }

    @Override
    public void stop() {
        if (dynamodb != null) {
            dynamodb.stop();
        }
    }

    private void initTable(String endpoint) {
        try {
            DynamoDbClient ddb = DynamoDbClient.builder()
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .endpointOverride(new URI(endpoint))
                    .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy", "dummy")))
                    .region(Region.EU_CENTRAL_1)
                    .build();

            ddb.createTable(builder -> builder
                    .tableName("dasniko-funqy-books")
                    .attributeDefinitions(AttributeDefinition.builder().attributeName("id").attributeType(ScalarAttributeType.S).build())
                    .keySchema(KeySchemaElement.builder().attributeName("id").keyType(KeyType.HASH).build())
                    .provisionedThroughput(ptb -> ptb.readCapacityUnits(100L).writeCapacityUnits(100L))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}