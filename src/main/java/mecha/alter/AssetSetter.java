package mecha.alter;

import mecha.alter.monsters.MON_Ahool;
import mecha.alter.monsters.MON_Banaspati;
import mecha.alter.monsters.MON_Jerangkong;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setMonsters() {
		gp.monster[0] = new MON_Ahool(gp);
		gp.monster[0].worldX = gp.tileSize * 12;
		gp.monster[0].worldY = gp.tileSize * 30;
		
		gp.monster[1] = new MON_Ahool(gp);
		gp.monster[1].worldX = gp.tileSize * 27;
		gp.monster[1].worldY = gp.tileSize * 14;
		
		gp.monster[2] = new MON_Jerangkong(gp);
		gp.monster[2].worldX = gp.tileSize * 26;
		gp.monster[2].worldY = gp.tileSize * 12;
		
		gp.monster[3] = new MON_Jerangkong(gp);
		gp.monster[3].worldX = gp.tileSize * 15;
		gp.monster[3].worldY = gp.tileSize * 28;
		
		gp.monster[4] = new MON_Banaspati(gp);
		gp.monster[4].worldX = gp.tileSize * 18;
		gp.monster[4].worldY = gp.tileSize * 28;
		
		gp.monster[5] = new MON_Banaspati(gp);
		gp.monster[5].worldX = gp.tileSize * 24;
		gp.monster[5].worldY = gp.tileSize * 9;
	}
}