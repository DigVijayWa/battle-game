package game.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapMaker {


  public static int[][] getRGBArray() {
    try {
      ClassLoader classLoader = MapMaker.class.getClassLoader();
      BufferedImage image = ImageIO.read(new File(classLoader.getResource("map.png").getFile()));

      int width = image.getWidth();
      int height = image.getHeight();

      int[][] bitMap = new int[width][height];

      for (int i = 0; i < width; i++) {

        for (int j = 0; j < height; j++) {
          bitMap[i][j] = image.getRGB(i, j);
        }
      }
      return bitMap;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
