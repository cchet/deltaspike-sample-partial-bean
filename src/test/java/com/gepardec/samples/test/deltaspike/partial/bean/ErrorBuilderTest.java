package com.gepardec.samples.test.deltaspike.partial.bean;

import com.gepardec.samples.deltaspike.partial.bean.api.CoreErrorBuilderApi;
import com.gepardec.samples.deltaspike.partial.bean.api.ErrorBuilder;
import org.apache.deltaspike.testcontrol.api.TestControl;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Thomas Herzog <thomas.herzog@gepardec.com>
 * @since 8/29/18
 */
@RunWith(CdiTestRunner.class)
public class ErrorBuilderTest {

    // -- Then --
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    @Named("coreErrorApi")
    private CoreErrorBuilderApi coreErrorBuilder;

    @Test
    @TestControl(startScopes = {ApplicationScoped.class})
    public void test_forIllegalArgument_with_cause() throws Throwable {
        // -- Given --
        final Throwable cause = new NumberFormatException();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(new IsEqual<>(cause));

        // -- When --
        throw coreErrorBuilder.forIllegalArgument()
                              .withCause(cause)
                              .build();
    }

    @Test
    @TestControl(startScopes = {ApplicationScoped.class})
    public void test_forIllegalArgument_overwritten() throws Throwable {
        // -- Given --
        final String module = "newModule";
        final String code = "newCode";
        final String errorPrefix = "newErrorPrefix";
        final String message = "new message";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(String.format(ErrorBuilder.PATTERN_MESSAGE,
                                                      module, errorPrefix, code, message));

        // -- When --
        throw coreErrorBuilder.forIllegalArgument()
                              .withNewMessage(message)
                              .withModule(module)
                              .withErrorCode(code)
                              .withErrorPrefix(errorPrefix)
                              .build();
    }
}
