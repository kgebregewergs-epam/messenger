package com.epam.ld.module2.testing;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this is a class that will be used to run all the unit tests
 * that have a @UnitTest tags.
 */
public class MessengerTestSuit {
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("UnitTest")
    @Test
    public @interface UnitTest {

    }
}
