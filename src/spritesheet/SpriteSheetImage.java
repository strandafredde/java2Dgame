package spritesheet;


import java.awt.image.BufferedImage;

public class SpriteSheetImage {

    private BufferedImage image;
    public SpriteSheetImage(BufferedImage ss){
        this.image = ss;

    }

    public BufferedImage getImage(int col, int row, int width, int height) {

        return image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);

    }
}
