package rocks.aereo.qrcodeapi.type;

import java.text.MessageFormat;

public class WIFI {

    private String authentication;
    private String ssid;
    private String password;
    private boolean isHidden;


    public WIFI(String authentication, String ssid, String password, boolean isHidden) {
        this.authentication = authentication;
        this.ssid = ssid;
        this.password = password;
        this.isHidden = isHidden;
    }

    public String encodeData() {
        Object[] params = new Object[]{"WIFI:T:", ";S:", ";P:", ";H:", ";;"};
        if (!isHidden) {
            return MessageFormat.format(
                    "{0}" + authentication +
                    "{1}" + ssid +
                    "{2}" + password +
                    "{4}"
                    , params);
        } else {
            return MessageFormat.format(
                    "{0}" + authentication +
                    "{1}" + ssid +
                    "{2}" + password +
                    "{3}" + "true" +
                    "{4}"
                    , params);
        }
    }
}
