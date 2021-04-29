package com.macky.springbootshardingjdbc.test.lambda_test;

@FunctionalInterface
public interface TestFunctionInterface<U> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    U get(U u);
}
