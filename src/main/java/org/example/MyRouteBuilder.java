package org.example;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.LoggingLevel;
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

    @EndpointInject(uri = "activemq:queue:FilaBackUp")
    private Endpoint filabackup;

    public void configure() {

        onException(Exception.class).log(LoggingLevel.INFO,"Erro durante o processamento");

        from("file:src/data?noop=true").routeId("Route1").log("${body}").to(filaOrigem).to(filabackup);

        from(filaOrigem).routeId("Route2").process(processorQualquer).log("${body}").to(filaDestino);

    }

}