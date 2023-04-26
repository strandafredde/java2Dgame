package entity;

import main.GamePanel;
import main.KeyHandler;
import spritesheet.SpriteSheetImage;
import spritesheet.SpriteSheetLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    String direction;

    GamePanel gp;
    KeyHandler keyH;
    BufferedImage spritesheet;

    public Player(GamePanel gp, KeyHandler keyH)  {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        // Set player's default values such as position, speed...
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        SpriteSheetLoader loader = new SpriteSheetLoader();
        try {
            spritesheet = loader.loadImage("/playerSheet.png");

        }
        catch(IOException e) {
            System.out.println("error: livet suger");
            e.printStackTrace();
        }
        SpriteSheetImage ss = new SpriteSheetImage(spritesheet);
        //Walking up images
        up1 = ss.getImage(2,4,32,32);
        up2 = ss.getImage(1,4,32,32);
        up3 = ss.getImage(3,4,32,32);
        //Walking left images
        left1 = ss.getImage(2,2,32,32);
        left2 = ss.getImage(1,2,32,32);
        left3 = ss.getImage(3,2,32,32);
        //Walking down images
        down1 = ss.getImage(2,1,32,32);
        down2 = ss.getImage(1,1,32,32);
        down3 = ss.getImage(3,1,32,32);
        //Walking right images
        right1 = ss.getImage(2,3,32,32);
        right2 = ss.getImage(1,3,32,32);
        right3 = ss.getImage(3,3,32,32);
    }
    public void update() {
        if(!keyH.upPressed && !keyH.leftPressed && !keyH.downPressed && !keyH.rightPressed) {
            walking = false;
        }
        if(keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed) {
            walking = true;
            //Player movement
            if(keyH.upPressed) {
                direction = "up";
                y -= speed;
            }
            else if(keyH.leftPressed) {
                direction = "left";
                x -= speed;
            }
            else if(keyH.downPressed) {
                direction = "down";
                y += speed;
            }
            else if(keyH.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 3;
                }
                else if(spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 3) {
                    image = up3;
                }
                if(!walking){
                    image = up1;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 3) {
                    image = left3;
                }
                if(!walking){
                    image = left1;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 3) {
                    image = down3;
                }
                if(!walking){
                    image = down1;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                if(!walking){
                    image = right1;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
