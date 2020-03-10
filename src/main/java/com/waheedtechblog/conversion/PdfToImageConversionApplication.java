package com.waheedtechblog.conversion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.image.BufferedImage;
import java.io.File;
import com.spire.pdf.PdfDocument;
import javax.imageio.ImageIO;

/**
 * Application to convert PDF to Images
 */
@SpringBootApplication
public class PdfToImageConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfToImageConversionApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			// save few entry of users
			PdfDocument document = new PdfDocument();
			//load pdf file
			document.loadFromFile("LeaveAndLicense.pdf");

			//save every PDF to .png image
			BufferedImage image;
			for (int i = 0; i < document.getPages().getCount(); i++) {
				// read every single page and save as single image
				image = document.saveAsImage(i);
				File file = new File( String.format("LeaveAndLicense-%d.png", i));
				ImageIO.write(image, "png", file);
			}
			document.close();
		};
	}

}
