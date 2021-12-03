package be.d2l.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("be.d2l")
public class CustomProperties {
    private String basketApiUrl;

    public String getBasketApiUrl() {
        return basketApiUrl;
    }


    public void setBasketApiUrl(String basketApiUrl) {
        this.basketApiUrl = basketApiUrl;
    }
}
