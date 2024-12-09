package org.nasva.config;

import org.aeonbits.owner.Config;

@Config.Sources(
        {"classpath:UrlsConfig.properties"}
)
public interface UrlsConfig extends Config {
    String baseUrl();
}
