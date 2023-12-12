package mecha.alter;

import mecha.alter.entity.Entity;

public class CollisionDetection 
{
	GamePanel gp;

	public CollisionDetection(GamePanel gp) 
	{
		this.gp = gp;
	}

	public void checkTIle(Entity entity) 
	{
		int entLeftWorldX = entity.worldX + entity.collideBox.x;
		int entRightWorldX = entity.worldX + entity.collideBox.x + entity.collideBox.width;
		int entTopWorldY = entity.worldY + entity.collideBox.y;
		int entBottomWorldY = entity.worldY + entity.collideBox.y + entity.collideBox.height;

		int entLeftCol = entLeftWorldX / gp.tileSize;
		int entRightCol = entRightWorldX / gp.tileSize;
		int entTopRow = entTopWorldY / gp.tileSize;
		int entBottomRow = entBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "up":
			entTopRow = (entTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
			tileNum2 = gp.tileM.mapTileNum[entRightCol][entTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		case "down":
			entBottomRow = (entBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entLeftCol][entBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entRightCol][entBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		case "left":
			entLeftCol = (entLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
			tileNum2 = gp.tileM.mapTileNum[entLeftCol][entBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		case "right":
			entRightCol = (entRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entRightCol][entTopRow];
			tileNum2 = gp.tileM.mapTileNum[entRightCol][entBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		}
	}
}