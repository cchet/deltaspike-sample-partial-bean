package com.gepardec.samples.deltaspike.partial.bean.internal;

import com.gepardec.samples.deltaspike.partial.bean.annotation.Error;
import com.gepardec.samples.deltaspike.partial.bean.annotation.ErrorBuilderApi;
import com.gepardec.samples.deltaspike.partial.bean.api.ErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * This type is a CDI bean in the application scope and creates the {@link ErrorBuilder} instance for the proxied type.
 *
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
@ApplicationScoped
@ErrorBuilderApi
public class ErrorBuilderInvocationHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorBuilderInvocationHandler.class);

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {
        log.info(String.format("Creating ErrorBuilder instance for type '%s'", method.getDeclaringClass().getName()));

        final ErrorBuilderApi errorApi = Objects.requireNonNull(method.getDeclaringClass().getAnnotation(ErrorBuilderApi.class),
                                                                "Class must be annotated with @ErrorBuilderApi");
        final Error error = Objects.requireNonNull(method.getAnnotation(Error.class),
                                                   "Method needs to be annotated with @Error");

        return new ErrorBuilderImpl().withModule(errorApi.module())
                                     .withErrorCode(error.code())
                                     .withErrorPrefix(errorApi.errorPrefix())
                                     .withException(error.exceptionClass())
                                     .withMessage(error.message());
    }
}
