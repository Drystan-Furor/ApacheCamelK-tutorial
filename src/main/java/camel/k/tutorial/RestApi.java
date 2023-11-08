package camel.k.tutorial;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {

    @Value("${server.port}")
    private int serverPort;

    @Value("${baeldung.api.path}")
    private String contextPath;

    @Override
    public void configure() {
        restConfiguration()
                .contextPath(contextPath)
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest("/api/")
                .id("api-route")
                .consumes("application/json")
                .post("/bean")
                .bindingMode(RestBindingMode.json_xml)
                .type(MyBean.class)
                .to("direct:remoteService");

        // Additional route definitions...
    }
}

/*
In this class, we override the configure() method from Camel’s RouteBuilder class.

Camel always needs a CamelContext instance – the core component where the incoming and outgoing messages are kept.

In this simple example, DefaultCamelContext suffices as it just binds messages and routes into it,
like the REST service that we are going to create.
 */
