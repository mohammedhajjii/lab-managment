package md.hajji.equipmentservice.services;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeService {
    byte[] generateQrCodeFromString(String source);
}
