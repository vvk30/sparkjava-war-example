package com.xoriant.demo;

import spark.Service;

/**
 * @author kulkarni_vs
 *
 */
@FunctionalInterface
public interface ServiceInitializer {

    /**
     * @param service just another service
     */
    void init(Service service);

}
