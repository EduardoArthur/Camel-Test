package org.example;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Autowired
    private ProcessorQualquer processorQualquer;

    @EndpointInject(uri = "activemq:queue:FilaOrigem")
    private Endpoint filaOrigem;

    @EndpointInject(uri = "activemq:queue:FilaDestino")
    private Endpoint filaDestino;

    public void configure() {

        from("file:src/data?noop=true").routeId("Route1").log("${body}").to(filaOrigem);

        from(filaOrigem).routeId("Route2").process(processorQualquer).to(filaDestino);

    }

}