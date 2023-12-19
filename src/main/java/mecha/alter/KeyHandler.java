package mecha.alter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mecha.alter.GamePanel.GAMESTATE;

public class KeyHandler implements KeyListener {

	GamePanel gp;

	public boolean upPressed, downPressed, leftPressed, rightPressed;

	boolean drawTimeInfo = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}

		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}

		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_P) 
		{
			if (GamePanel.state == GAMESTATE.GAME) 
			{
				GamePanel.state = GAMESTATE.PAUSED;
			} 
			else if (GamePanel.state == GAMESTATE.PAUSED) 
			{
				GamePanel.state = GAMESTATE.GAME;
			}
		}
		
		if(code == KeyEvent.VK_ESCAPE)
		{
			if (GamePanel.state == GAMESTATE.PAUSED)
			{
				GamePanel.state = GAMESTATE.GAME;
			}
		}
		
		if (code == KeyEvent.VK_I) {
			if (!drawTimeInfo) {
				drawTimeInfo = true;
			} else if (drawTimeInfo) {
				drawTimeInfo = false;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}

		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}

		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
}