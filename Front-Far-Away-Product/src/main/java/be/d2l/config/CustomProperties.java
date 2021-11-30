package d2l.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("be.d2l")
public class CustomProperties {

    private String commentApiUrl;

    public String getCommentApiUrl() {
        return commentApiUrl;
    }

    public void setCommentApiUrl(String commentApiUrl) {
        this.commentApiUrl = commentApiUrl;
    }
}
