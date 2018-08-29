package com.gepardec.samples.deltaspike.partial.bean.annotation;

import java.lang.annotation.*;

/**
 * This annotation specifies an error for a {@link com.gepardec.samples.deltaspike.partial.bean.api.ErrorBuilder} instance on method level
 *
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Error {

    String code();

    String message();

    Class<? extends Throwable> exceptionClass() default RuntimeException.class;
}
