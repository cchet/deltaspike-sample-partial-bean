= Sample for DeltaSpike Partial-Bean Module

This sample provides a use case for the DeltaSpike Partial-Bean Modul, which can be used to avoid boileplate code, by implementing a generic handler, which reads meta-data provided by the proxied bean, and uses this meta-data for creating a method result.

== What does this sample show ?
This sample implements a partial bean, which defines errors of an application in an interface. The interface defines a method per application error, and provides static information about the error in form of annotations on type and method level. The generic handler creates an error builder, which is pre-configured with the available meta-data, present either on type or method level. The pre-configured error builder can be used to provide additional information about the error, before creating the exception representing the application error.

.The interface defining the partial bean 
[source,java]
----
@Named("coreErrorApi")
@ApplicationScoped
@ErrorBuilderApi(module = "CORE", errorPrefix = "ER")
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
----

.Example showing how to use the partial bean
[source,java]
----
@ApplicationScoped
public class ServiceBean {

    @Inject
    @Named("coreErrorApi")
    private CoreErrorBuilderApi coreErrorBuilder;
    
    public void doStuff(final String data) {
        Optional.ofNullable(data)
                .orElseThrow(() -> coreErrorBuilder.forIllegalArgument()
                                                   .build());
        ...
    }
}
----

== How to run the sample ?
This sample does not provide a runtime environment, but does implement tests, which show how to use the partial bean in a CDI environment. The tests use the https://svn.apache.org/repos/infra/websites/production/deltaspike/content/retired/test-control.html[DeltaSpike Test-Control Module], which provides a convenient way to perform tests in a CDI environment. +

.Command for building and testing the sample
[source,bash]
----
mvn clean install
----




