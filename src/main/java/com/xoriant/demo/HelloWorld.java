package com.xoriant.demo;

import spark.servlet.SparkApplication;

import static spark.Spark.get;
import static spark.Spark.notFound;

/**
 * @author kulkarni_vs
 *
 */
public class HelloWorld implements SparkApplication {
    /**
     * @param args CMD arguments
     */
    public static void main(final String[] args) {
        new HelloWorld().init();
    }

    /*
     * (non-Javadoc)
     * @see spark.servlet.SparkApplication#init()
     */
    @Override
    public final void init() {
        get("/hello", (req, res) -> "Hello World");
        get("/hello/:name", (request, response) ->
        "Hello: " + request.params(":name"));
        get("/say/*/to/*", (request, response) ->
        "Number of splat parameters: " + request.splat().length);
        // Using Route
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"404\"}";
        });
    }
}
