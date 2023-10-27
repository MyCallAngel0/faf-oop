package org.oop.lab.two.filetype;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImageFile extends FileType {

    private int width;
    private int height;

    public ImageFile(String filename, String filePath) {
        super(filename, filePath);
        getAdditionalFileInfo();

    }

    @Override
    protected void getAdditionalFileInfo() {
        try {
            BufferedImage image = ImageIO.read(this.file);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getInfo() {
        System.out.println("Image width: " + this.width);
        System.out.println("Image height: " + this.height);

    }
}
