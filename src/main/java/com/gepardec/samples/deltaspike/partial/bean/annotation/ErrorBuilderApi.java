package com.gepardec.samples.deltaspike.partial.bean.annotation;

import org.apache.deltaspike.partialbean.api.PartialBeanBinding;

import java.lang.annotation.*;

/**
 * This interface specifies an error builder api.
 *
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PartialBeanBinding
public @interface ErrorBuilderApi {

    String module() default "";

    String errorPrefix() default "ERROR";
}
