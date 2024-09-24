package md.hajji.equipmentservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "equipment.qrcode.dimension")
public record QrCodeDimension(
        int width,
        int height
) {}
