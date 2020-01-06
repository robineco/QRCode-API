package rocks.aereo.qrcodeapi.type;

import lombok.extern.java.Log;

import java.text.MessageFormat;

@Log
public class GEO {

    private String lat;
    private String lon;

    public GEO(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String encodeData() {
        Object[] params = new Object[]{"geo:", ","};
        String msg = MessageFormat.format("{0}" + lat + "{1}" + lon + "{1}1", params);
        log.info(msg);
        return msg;
    }
}
