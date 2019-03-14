package com.xoriant.demo;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SparkServerClassRuleTest {

    private Client client;

    @ClassRule
    public static final SparkServerRule SPARK_SERVER = new SparkServerRule(http -> {
        http.get("/ping", (request, response) -> "pong");
        http.get("/health", (request, response) -> "healthy");
    });

    @After
    public void tearDown() {
        Optional.ofNullable(client).ifPresent(Client::close);
    }

    @Test
    public void testSparkServerRule_PingRequest() {
        client = ClientBuilder.newBuilder().build();
        Response response = client.target(URI.create("http://localhost:4567/ping"))
                .request()
                .get();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(String.class)).isEqualTo("pong");
    }

    @Test
    public void testSparkServerRule_HealthRequest() {
        client = ClientBuilder.newBuilder().build();
        Response response = client.target(URI.create("http://localhost:4567/health"))
                .request()
                .get();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(String.class)).isEqualTo("healthy");
    }

}