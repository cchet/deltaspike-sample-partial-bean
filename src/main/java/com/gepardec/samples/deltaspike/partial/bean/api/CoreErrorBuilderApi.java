package com.gepardec.samples.deltaspike.partial.bean.api;

import com.gepardec.samples.deltaspike.partial.bean.annotation.Error;
import com.gepardec.samples.deltaspike.partial.bean.annotation.ErrorBuilderApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * This interface specifies errors and is proxied by DeltaSpike Partial-Bean Module.
 * This type will be available in the CDI container.
 *
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
@Named("coreErrorApi")
@ApplicationScoped
@ErrorBuilderApi(module = "CORE", errorPrefix = "ER-")
public interface CoreErrorBuilderApi extends Serializable {

    @Error(code = "00001",
            message = "An illegal stage has been detected",
            exceptionClass = IllegalStateException.class)
    ErrorBuilder forIllegalState();

    @Error(code = "00002",
            message = "An illegal argument has been detected",
            exceptionClass = IllegalArgumentException.class)
    ErrorBuilder forIllegalArgument();
}
