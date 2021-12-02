package be.d2l.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class Utils {

    public Cookie createCookie(String token) {
        if(token == null) throw new IllegalArgumentException("Token is null");
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        return cookie;
    }
}
