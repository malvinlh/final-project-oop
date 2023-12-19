package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import mecha.alter.GamePanel.GAMESTATE;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	public String message = "";
	public boolean messageOn = false;
	int messageCounter = 0;

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);

		if (GamePanel.state == GAMESTATE.PAUSED) {
			drawPauseScreen();
		}

	}

	private void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);

	}

	private int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}