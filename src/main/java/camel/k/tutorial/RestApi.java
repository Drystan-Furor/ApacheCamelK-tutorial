package camel.k.tutorial;

import org.apache.camel.Exchange;
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

        from("direct:remoteService")
                .routeId("direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .transform().simple("Hello ${in.body.name}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

        // Additional route definitions...
    }
}

/*
In this class, we override the configure() method from Camel’s RouteBuilder class.

Camel always needs a CamelContext instance – the core component where the incoming and outgoing messages are kept.

In this simple example, DefaultCamelContext suffices as it just binds messages and routes into it,
like the REST service that we are going to create.


The from() method follows the same principles and has many of the same methods as the rest() method, except that it consumes from the Camel context messages. This is the reason for the parameter “direct-route“, that creates a link to the aforementioned method rest().to().

Many other conversions are available, including extraction as Java primitives (or objects) and sending it down to a persistence layer. Notice that the routes always read from incoming messages, so that chained routes will ignore outgoing messages.

Our example is ready, and we can try it:

Run the prompt command: mvn spring-boot:run
Do a POST request to http://localhost:8080/camel/api/bean with header parameters: Content-Type: application/json, and a payload {“id”: 1,”name”: “World”}
We should receive a return code of 201 and the response: Hello, World
 */
