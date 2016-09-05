package no.imr.nmdapi.client.loader.routes;

import no.imr.nmdapi.client.loader.processor.ExceptionProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author sjurl
 */
public class InitRoute extends RouteBuilder {

    @Autowired
    @Qualifier("referenceConfig")
    private PropertiesConfiguration configuration;

    @Override
    public void configure() throws Exception {
        onException(Exception.class).continued(true).process(new ExceptionProcessor(configuration.getString("application.name"))).to("jms:queue:error");
        from("timer://runOnce?repeatCount=1&delay=5000").to("fixedCoastalStationLoader","platformLoader", "nationLoader", "acousticcategoryLoader",
                "equipmentLoader", "institutionLoader", "missiontypeLoader", "personLoader", "seaareaLoader", "taxaLoader", "languageLoader", "udpLoader");
    }

}
