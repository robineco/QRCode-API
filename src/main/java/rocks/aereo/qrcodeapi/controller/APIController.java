package rocks.aereo.qrcodeapi.controller;

import net.glxn.qrgen.QRCode;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rocks.aereo.qrcodeapi.type.Generator;
import rocks.aereo.qrcodeapi.type.Type;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class APIController {

    @GetMapping(
            path = "/gen",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] generate(@RequestParam MultiValueMap<String, String> params) {
        Map<String, String> data = new HashMap<>();
        ByteArrayOutputStream stream = null;
        if (params.containsKey("type")) {
            switch (params.getFirst("type")) {
                case "text":
                    data.put("text", params.getFirst("data"));
                    Generator code = new Generator(Type.TEXT, data);
                    stream = code.generateQRCode();
                    break;
                case "url":
                    data.put("url", params.getFirst("data"));
                    Generator url = new Generator(Type.URL, data);
                    stream = url.generateQRCode();
                    break;
                case "wifi":
                    data.put("auth", params.getFirst("auth"));
                    data.put("ssid", params.getFirst("ssid"));
                    data.put("pw", params.getFirst("pw"));
                    data.put("hidden", params.getFirst("hidden"));
                    Generator wifi = new Generator(Type.WIFI, data);
                    stream = wifi.generateQRCode();
                    break;
                case "vcard":
                    data.put("name", params.getFirst("name"));
                    data.put("email", params.getFirst("email"));
                    data.put("address", params.getFirst("address"));
                    data.put("company", params.getFirst("company"));
                    data.put("tel", params.getFirst("tel"));
                    data.put("web", params.getFirst("web"));
                    Generator vcard = new Generator(Type.VCARD, data);
                    stream = vcard.generateQRCode();
                    break;
                case "geo":
                    data.put("lat", params.getFirst("lat"));
                    data.put("lon", params.getFirst("lon"));
                    Generator geo = new Generator(Type.GEO, data);
                    stream = geo.generateQRCode();
                    break;
                default:
                    stream = QRCode.from("https://github.com/robineco").stream();
            }

            return stream.toByteArray();
        }
        return "error".getBytes();
    }
}
