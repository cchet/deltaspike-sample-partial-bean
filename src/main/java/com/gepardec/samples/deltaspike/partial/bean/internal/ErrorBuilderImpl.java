package com.gepardec.samples.deltaspike.partial.bean.internal;

import com.gepardec.samples.deltaspike.partial.bean.api.ErrorBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This is the error builder implementation.
 *
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
public class ErrorBuilderImpl implements ErrorBuilder {

    private String code;
    private String module;
    private String errorPrefix;
    private Class<? extends Throwable> exceptionClass;
    private Throwable cause;
    private List<String> messages = new LinkedList<>();

    @Override
    public ErrorBuilder withCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    @Override
    public ErrorBuilder withException(Class<? extends Throwable> exceptionClass) {
        this.exceptionClass = Objects.requireNonNull(exceptionClass, "Cannot set null exception class");
        return this;
    }

    @Override
    public ErrorBuilder withModule(String module) {
        this.module = Objects.requireNonNull(module, "Cannot set null module");
        return this;
    }

    @Override
    public ErrorBuilder withErrorPrefix(String errorPrefix) {
        this.errorPrefix = Objects.requireNonNull(errorPrefix, "Cannot set null errorPrefix");
        return this;
    }

    @Override
    public ErrorBuilder withErrorCode(String code) {
        this.code = Objects.requireNonNull(code, "Cannot set null error code");
        return this;
    }

    @Override
    public ErrorBuilder withMessage(String message) {
        messages.add(Objects.requireNonNull(message, "Cannot add nul message"));
        return this;
    }

    @Override
    public ErrorBuilder withNewMessage(String message) {
        messages.clear();
        return withMessage(message);
    }

    @Override
    public <T extends Throwable> T build() {
        Throwable instance;
        try {
            instance = Objects.requireNonNull(exceptionClass,
                                              "Cannot instantiate null exception class")
                              .getConstructor(String.class, Throwable.class)
                              .newInstance(createMessage(), cause);
        } catch (Throwable e) {
            throw new IllegalStateException("Could not instantiate the defined exception", e);
        }

        return (T) instance;
    }

    private String createMessage() {
        return String.format(PATTERN_MESSAGE, Optional.ofNullable(module).orElse("undefined"),
                             Optional.ofNullable(errorPrefix).orElse("undefined"),
                             Optional.ofNullable(code).orElse("undefined"),
                             String.join(",", messages));
    }
}
