package rocks.aereo.qrcodeapi.type;

import lombok.extern.java.Log;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.vcard.VCard;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Log
public class Generator {

    public static final int SIZE = 500;
    private Type type;
    private Map<String, String> data;

    public Generator(Type type, Map<String, String> data) {
        this.type = type;
        this.data = data;
        log.info("created: " + type.toString() + " || " + data);
    }

    public ByteArrayOutputStream generateQRCode() {
        switch (type) {
            case TEXT:
                return QRCode.from(encodeText()).withSize(SIZE, SIZE).stream();
            case URL:
                return QRCode.from(encodeURL()).withSize(SIZE, SIZE).stream();
            case WIFI:
                return QRCode.from(encodeWIFI()).withSize(SIZE, SIZE).stream();
            case VCARD:
                return QRCode.from(encodeVCard()).withSize(SIZE, SIZE).stream();
            case GEO:
                return QRCode.from(encodeGEO()).withSize(SIZE, SIZE).stream();
            default:
                return QRCode.from("https://github.com/robineco").withSize(255, 255).stream();
        }
    }

    private String encodeText() {
        return data.get("text");
    }

    private String encodeURL() {
        return data.get("url");
    }

    private String encodeWIFI() {
        return new WIFI(
                data.get("auth"),
                data.get("ssid"),
                data.get("pw"),
                Boolean.parseBoolean(data.get("hidden"))
        ).encodeData();
    }

    private VCard encodeVCard() {
        return new VCard(data.get("name"))
                .setEmail(data.get("email"))
                .setAddress(data.get("address"))
                .setCompany(data.get("company"))
                .setPhonenumber(data.get("tel"))
                .setWebsite(data.get("web"));
    }

    private String encodeGEO() {
        return new GEO(data.get("lat"), data.get("lon")).encodeData();
    }
}
