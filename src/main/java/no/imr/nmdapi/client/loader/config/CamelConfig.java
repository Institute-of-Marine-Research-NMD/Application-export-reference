package no.imr.nmdapi.client.loader.config;

import java.util.Arrays;
import java.util.List;
import no.imr.nmdapi.client.loader.routes.InitRoute;
import no.imr.nmdapi.client.loader.routes.UpdateRoute;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for camel
 *
 * @author sjurl
 */
@Configuration
public class CamelConfig extends CamelConfiguration implements InitializingBean {

    @Autowired
    private InitRoute initRoute;

    @Autowired
    private UpdateRoute updateRoute;

    @Override
    public List<RouteBuilder> routes() {
        return Arrays.asList(initRoute, updateRoute);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // no properties loaded so not used
    }

}
