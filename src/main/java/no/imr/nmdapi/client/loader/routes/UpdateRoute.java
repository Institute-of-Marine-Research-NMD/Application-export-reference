package no.imr.nmdapi.client.loader.routes;

import no.imr.nmdapi.client.loader.processor.ExceptionProcessor;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author sjurl
 */
public class UpdateRoute extends RouteBuilder {

    @Autowired
    @Qualifier("referenceConfig")
    private PropertiesConfiguration configuration;

    @Override
    public void configure() throws Exception {
        Predicate platform = body(String.class).contains("platform");
        Predicate nation = body(String.class).contains("nation");

        Predicate taxa = body(String.class).contains("taxa");
        Predicate language = body(String.class).contains("language");
        Predicate restriction = body(String.class).contains("restriction");
        Predicate stock = body(String.class).contains("stock");
        onException(Exception.class).handled(true).process(new ExceptionProcessor(configuration.getString("application.name"))).to("jms:queue:error");

        from("jms:queue:export-nmdreference")
                .errorHandler(deadLetterChannel("jms:queue:dead").maximumRedeliveries(3).redeliveryDelay(30000))
                .choice()
                .when(body(String.class).in(platform, nation)).to("platformLoader", "nationLoader")
                .when(body(String.class).contains("acousticcategory")).to("acousticcategoryLoader")
                .when(body(String.class).contains("equipment")).to("equipmentLoader")
                .when(body(String.class).contains("institution")).to("institutionLoader")
                .when(body(String.class).contains("missiontype")).to("missiontypeLoader")
                .when(body(String.class).contains("person")).to("personLoader")
                .when(body(String.class).contains("seaarea")).to("seaareaLoader")
                .when(body(String.class).in(taxa, language, restriction, stock)).to("taxaLoader", "languageLoader")
                .when(body(String.class).contains("udp")).to("udpLoader")
                .otherwise().to("jms:queue:export-nothandled");
    }

}
