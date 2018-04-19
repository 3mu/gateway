package mulin.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ProfileRoutePredicateFactory extends AbstractRoutePredicateFactory<ProfileRoutePredicateFactory.Config> {

    public ProfileRoutePredicateFactory() {
        super(Config.class);
    }

    public static final String key_Key = "key";
    public static final String domain_key = "domain";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(key_Key, domain_key);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            //To Do get request param to filter redis cache 
            String url = exchange.getRequest().getURI().toString();
            if (url.contains(config.domain))
                return true;
            else return false;
        };
    }

    @Validated
    public static class Config {
        private String key;
        private String domain;

        public Config() {
        }


        public String getKey() {
            return key;
        }

        public Config setKey(String key) {
            this.key = key;
            return this;
        }

        public String getDomain() {
            return domain;
        }

        public Config setDomain(String domain) {
            this.domain = domain;
            return this;
        }
    }
}
