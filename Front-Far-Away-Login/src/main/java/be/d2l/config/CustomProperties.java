package be.d2l.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("be.d2l")
public class CustomProperties {

    private String usersApiUrl;

    public String getUsersApiUrl() {
        return usersApiUrl;
    }

    public void setUsersApiUrl(String usersApiUrl) {
        this.usersApiUrl = usersApiUrl;
    }
}
