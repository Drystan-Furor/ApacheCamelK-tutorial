package camel.k.tutorial;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {
    @Override
    public void configure() {
        CamelContext context = new DefaultCamelContext();

        restConfiguration()...
        rest("/api/")...
        from("direct:remoteService")...
    }
}

/*
In this class, we override the configure() method from Camel’s RouteBuilder class.

Camel always needs a CamelContext instance – the core component where the incoming and outgoing messages are kept.

In this simple example, DefaultCamelContext suffices as it just binds messages and routes into it,
like the REST service that we are going to create.
 */
