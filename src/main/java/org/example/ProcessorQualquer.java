package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessorQualquer implements Processor {

    private final Logger log = LoggerFactory.getLogger(ProcessorQualquer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws IOException {
        String body = exchange.getIn().getBody(String.class);

        log.info("Mensagem entrando no processador");

        JsonDTO object = objectMapper.readValue(body,JsonDTO.class);

        object.setAuth(object.getAge() >= 18);

        log.info(object.toString());

        exchange.getIn().setBody(parseObjectToJson(object));

        log.info("Mensagem Processada com sucesso");
    }

    public String parseObjectToJson(Object obj){
        try {
            String Json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            log.debug(Json);
            return Json;
        }catch (JsonProcessingException e){
            String msg = "erro na conversão do objeto para Json";
            log.error(msg, e);
            return null;
        }

    }
}
