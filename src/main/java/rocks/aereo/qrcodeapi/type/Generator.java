package rocks.aereo.qrcodeapi.type;

import lombok.extern.java.Log;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;

@Log
public class Generator {

    private Type type;
    private String data;

    public Generator(Type type, String data) {
        this.type = type;
        this.data = data;
        System.out.println("created: " + type.toString() + " || "+ data);
    }

    public ByteArrayOutputStream generateQRCode() {
        ByteArrayOutputStream stream = QRCode.from(data).to(ImageType.JPG).stream();
        return stream;
    }
}
