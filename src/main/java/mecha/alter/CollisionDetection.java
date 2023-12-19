package mecha.alter;

import mecha.alter.entity.Entity;

public class CollisionDetection {
	GamePanel gp;

	public CollisionDetection(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.collideBox.x;
		int entityRightWorldX = entity.worldX + entity.collideBox.x + entity.collideBox.width;
		int entityTopWorldY = entity.worldY + entity.collideBox.y;
		int entityBottomWorldY = entity.worldY + entity.collideBox.y + entity.collideBox.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collide = true;
			}
			break;

		}

	}

	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				entity.collideBox.x = entity.worldX + entity.collideBox.x;
				entity.collideBox.y = entity.worldY + entity.collideBox.y;

				target[i].collideBox.x = target[i].worldX + target[i].collideBox.x;
				target[i].collideBox.y = target[i].worldY + target[i].collideBox.y;

				switch (entity.direction) {
				case "up":
					entity.collideBox.y -= entity.speed;
					break;
				case "down":
					entity.collideBox.y += entity.speed;
					break;
				case "left":
					entity.collideBox.x -= entity.speed;
					break;
				case "right":
					entity.collideBox.x += entity.speed;
					
					break;
				}
				if (entity.collideBox.intersects(target[i].collideBox)) {
					if(target[i] != entity) {
						entity.collide = true;
						index = i;
					}
				}
				entity.collideBox.x = entity.collideBoxDefaultX;
				entity.collideBox.y = entity.collideBoxDefaultY;
				target[i].collideBox.x = target[i].collideBoxDefaultX;
				target[i].collideBox.y = target[i].collideBoxDefaultY;
			}
		}
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer = false;
		entity.collideBox.x = entity.worldX + entity.collideBox.x;
		entity.collideBox.y = entity.worldY + entity.collideBox.y;

		gp.player.collideBox.x = gp.player.worldX + gp.player.collideBox.x;
		gp.player.collideBox.y = gp.player.worldY + gp.player.collideBox.y;

		switch (entity.direction) {
		case "up":
			entity.collideBox.y -= entity.speed;
			break;
		case "down":
			entity.collideBox.y += entity.speed;
			break;
		case "left":
			entity.collideBox.x -= entity.speed;
			break;
		case "right":
			entity.collideBox.x += entity.speed;
			break;
		}
		if (entity.collideBox.intersects(gp.player.collideBox)) {
			entity.collide = true;
			contactPlayer = true;
		}
		entity.collideBox.x = entity.collideBoxDefaultX;
		entity.collideBox.y = entity.collideBoxDefaultY;
		gp.player.collideBox.x = gp.player.collideBoxDefaultX;
		gp.player.collideBox.y = gp.player.collideBoxDefaultY;
		return contactPlayer;
	}
}