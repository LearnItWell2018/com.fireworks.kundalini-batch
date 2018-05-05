package com.fireworks.kundalini.task;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeGeneratorTasklet implements Tasklet {

	private String dir;
	private String ext;
	private String logo;
	private String CONTENT = "Pranab Subha Harpal Debaprasad Dibakar Suparna";
	private int width = 3000;
	private int hight = 3000;
	
	Environment env;
	
	@Autowired
	public QrCodeGeneratorTasklet(Environment env) {
		this.env = env;
		this.logo = "file:/" + env.getProperty("imagePath") + "kundalini.jpg";
		this.dir = env.getProperty("qrCodeGenPath");
		this.width = Integer.parseInt(env.getProperty("qrIMGWidth"));
		this.hight = Integer.parseInt(env.getProperty("qrIMGHight"));
		this.ext = env.getProperty("qrIMGExt");
	}
	
	
	public void generate(String qrCodeName) {
		// Create new configuration that specifies the error correction
		Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			// init directory
			initDirectory(dir);
			// Create a qr code with the url as content and a size of WxH px
			bitMatrix = writer.encode(CONTENT, BarcodeFormat.QR_CODE, width, hight, hints);

			// Load QR image
			BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());

			// Load logo image
			BufferedImage overly = getOverly(logo);

			// Calculate the delta height and width between QR code and logo
			int deltaHeight = qrImage.getHeight() - overly.getHeight();
			int deltaWidth = qrImage.getWidth() - overly.getWidth();

			// Initialize combined image
			BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) combined.getGraphics();

			// Write QR code to new image at position 0/0
			g.drawImage(qrImage, 0, 0, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

			// Write logo into combine image at position (deltaWidth / 2) and
			// (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
			// the same space for the logo to be centered
			g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

			// Write combined image as PNG to OutputStream
			ImageIO.write(combined, "png", os);
			// Store Image
			Files.copy(new ByteArrayInputStream(os.toByteArray()), Paths.get(dir + qrCodeName + ext),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage getOverly(String LOGO) throws IOException {
		URL url = new URL(LOGO);
		return ImageIO.read(url);
	}

	private void initDirectory(String DIR) throws IOException {
		Files.createDirectories(Paths.get(DIR));
	}

	private MatrixToImageConfig getMatrixConfig() {
		return new MatrixToImageConfig(QrCodeGeneratorTasklet.Colors.WHITE.getArgb(),
				QrCodeGeneratorTasklet.Colors.RED.getArgb());
	}

	public enum Colors {

		BLUE(0xFF40BAD0), RED(0xFFE91C43), PURPLE(0xFF8A4F9E), ORANGE(0xFFF4B13D), WHITE(0xFFFFFFFF), BLACK(0xFF000000);

		private final int argb;

		Colors(final int argb) {
			this.argb = argb;
		}

		public int getArgb() {
			return argb;
		}
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		this.generate("final");
		return null;
	}
}