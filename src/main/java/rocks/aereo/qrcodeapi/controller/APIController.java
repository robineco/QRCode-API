package rocks.aereo.qrcodeapi.controller;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rocks.aereo.qrcodeapi.type.Generator;
import rocks.aereo.qrcodeapi.type.Type;
import rocks.aereo.qrcodeapi.type.WIFI;

import java.io.ByteArrayOutputStream;

@RestController
public class APIController {

    @GetMapping(
            path = "/gen",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] generate(@RequestParam MultiValueMap<String, String> params) {

        ByteArrayOutputStream stream = null;
        if (params.containsKey("type")) {
            switch (params.getFirst("type")) {
                case "text":
                    Generator code = new Generator(Type.TEXT, params.getFirst("data"));
                    stream = code.generateQRCode();
                    break;
                case "url":
                    Generator url = new Generator(Type.URL, params.getFirst("data"));
                    stream = url.generateQRCode();
                    break;
                case "wifi":
                    Generator wifi = new Generator(Type.WIFI, createWifi(params));
                    stream = wifi.generateQRCode();
                    break;
                default:
            }

            return stream.toByteArray();
        }
        return "error".getBytes();
    }

    private String createWifi(MultiValueMap<String, String> params) {
        String auth = params.getFirst("auth");
        String ssid = params.getFirst("ssid");
        String password = params.getFirst("pw");
        boolean isHidden = Boolean.parseBoolean(params.getFirst("hidden"));
        return new WIFI(auth, ssid, password, isHidden).encodeData();
    }

}
