package mecha.alter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTools {
	public BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		int imageType = BufferedImage.TYPE_INT_ARGB;

		BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, imageType);
		Graphics2D graphics2D = scaledImage.createGraphics();

		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);

		graphics2D.dispose();

		return scaledImage;
	}
}
