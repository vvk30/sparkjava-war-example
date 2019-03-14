package com.xoriant.demo;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;

/**
 * @author kulkarni_vs
 *
 */
public class SparkServerRule extends ExternalResource {

    /**
     * LOG used to get logger.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(SparkServerRule.class);

    /**
     * service initializer.
     */
    private ServiceInitializer serviceInitializer;
    /**
     * Just a service.
     */
    private Service service;

    /**
     * @param svcInit initialize svc.
     */
    public SparkServerRule(final ServiceInitializer svcInit) {
        this.serviceInitializer = svcInit;
    }

    /*
     * (non-Javadoc)
     * @see org.junit.rules.ExternalResource#before()
     */
    @Override
    protected final void before() throws Throwable {
        LOG.trace("Start spark server");
        service = Service.ignite();
        serviceInitializer.init(service);

        LOG.trace("Await initialization of Spark...");
        service.awaitInitialization();

        LOG.trace("Spark is ignited!");
    }

    @Override
    protected final void after() {
        LOG.trace("Stopping Spark...");
        service.stop();

        LOG.trace("Spark stopped");
    }

}
