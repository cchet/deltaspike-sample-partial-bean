package com.gepardec.samples.deltaspike.partial.bean.api;

/**
 * This interface specifies the operations vailable for error builders.
 *
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
public interface ErrorBuilder {

    String PATTERN_MESSAGE = "%s-%s-%s %s";

    ErrorBuilder withCause(Throwable cause);

    ErrorBuilder withException(Class<? extends Throwable> exceptionClass);

    ErrorBuilder withModule(String module);

    ErrorBuilder withErrorPrefix(String errorPrefix);

    ErrorBuilder withErrorCode(String code);

    ErrorBuilder withMessage(String message);

    ErrorBuilder withNewMessage(String message);

    <T extends Throwable> T build();
}
