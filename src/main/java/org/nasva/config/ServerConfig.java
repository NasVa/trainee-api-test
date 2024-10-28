package org.nasva.config;

import org.aeonbits.owner.Config;

@Config.Sources(
        {"classpath:UrlsConfig.properties"}
)
public interface ServerConfig extends Config {
    @Key("webapp.urls.baseUrl")
    String getBaseUrl();
}
