package md.hajji.equipmentservice.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import md.hajji.equipmentservice.configurations.QrCodeDimension;
import md.hajji.equipmentservice.services.QrCodeService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
@AllArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {

    private final QrCodeDimension qrCodeDimension;

    @Override
    @SneakyThrows({IOException.class, WriterException.class})
    public byte[] generateQrCodeFromString(String source){

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                source,
                BarcodeFormat.QR_CODE,
                qrCodeDimension.width(),
                qrCodeDimension.height()
        );

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        return pngOutputStream.toByteArray();
    }
}
