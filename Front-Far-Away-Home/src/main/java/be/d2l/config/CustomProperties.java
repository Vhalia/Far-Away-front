package be.d2l.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("be.d2l")
public class CustomProperties {

    private String productApiUrl;

    public String getProductApiUrl() {
        return productApiUrl;
    }

    public void setProductApiUrl(String productApiUrl) {
        this.productApiUrl = productApiUrl;
    }
}
