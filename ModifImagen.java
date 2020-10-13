import java.awt.image.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ModifImagen{

    public static BufferedImage binarizado(BufferedImage img){
        Color matrix[][];

        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        matrix = new Color[h][w];

        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j) {
                Color pixel = new Color(img.getRGB(j, i));
                int promedio = (pixel.getBlue() + pixel.getRed() + pixel.getBlue()) / 3;
                if (promedio < 100)
                    matrix[i][j] = Color.BLACK;
                else
                    matrix[i][j] = Color.WHITE;
            }

        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j)
                out.setRGB(j, i, matrix[i][j].getRGB());

        return out;
    }

    public static BufferedImage RGBadd(BufferedImage img, int addr, int addg, int addb) {
        Color matrix[][];

        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        matrix = new Color[h][w];

        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j){
                Color p = new Color(img.getRGB(j, i));
                int r = p.getRed() + addr;
                int g = p.getGreen() + addg;
                int b = p.getBlue() + addb;

                if(r > 255)
                    r = 255;
                if(r < 0)
                    r = 0;
                if(g > 255)
                    g = 255;
                if(g < 0)
                    g = 0;
                if(b > 255)
                    b = 255;
                if(b < 0)
                    b = 0;

                matrix[i][j] = new Color(r,g,b);
            }

        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j)
                out.setRGB(j, i, matrix[i][j].getRGB());

        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File(args[0]));

        boolean isInteger = false;
        try {
            Integer.parseInt(args[1]);
            isInteger = true;
        } catch (Exception e) {}
        if(isInteger){
            int addr = Integer.parseInt(args[1]);
            int addg = Integer.parseInt(args[2]);
            int addb = Integer.parseInt(args[3]);
            img = RGBadd(img,addr,addg,addb);
        }
        else
            img = binarizado(img);
        
        ImageIO.write(img, "jpg", new File("out.jpg"));
    }
}