package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorQualquer implements Processor {

    @Override
    public void process(Exchange exchange) {
        String body = exchange.getIn().getBody(String.class);

        System.out.println("Mensagem entrando no processador");

        System.out.println(body);

        exchange.getIn().setBody("Json novo");

        System.out.println("Mensagem Processada com sucesso");
    }
}
