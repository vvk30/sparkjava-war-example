package com.xoriant.demo;

import spark.Service;

@FunctionalInterface
public interface ServiceInitializer {

    void init(Service service);

}