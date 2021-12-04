package be.d2l.utils;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Utils {

    private Base64.Decoder decoder;

    public Utils(){
        this.decoder = Base64.getDecoder();
    }

    public  int getIntFromPayloadWithName(String name, String token){
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.getInt(name);
    }

    public String getStringFromPayloadWithName(String name, String token){
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));

        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.getString(name);
    }
}
