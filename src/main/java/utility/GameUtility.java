package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GameUtility {

  public static long getTimeInSeconds(long timePassed) {
    return (long) (timePassed / 1000_000_000.0);
  }

  public static long getTimeInMiliSeconds(long timePassed) {
    return (long) (timePassed / 1000_000.0);
  }

  public static double mapRandomValue(double randomValue) {
    return 0 + ((800)) * (randomValue - 0);
  }

  /**
   * Maps the given input to a defined range between outputStart - outputEnd.
   * @param input
   * @param inputStart
   * @param inputEnd
   * @param outputStart
   * @param outputEnd
   * @return
   */
  public static double mapRange(double input, double inputStart, double inputEnd,
      double outputStart,
      double outputEnd) {
    return outputStart + ((outputEnd - outputStart) / (inputEnd - inputStart)) * (input
        - inputStart);
  }

  /**
   * Generate random values in between range.
   * @param outputStart
   * @param outputEnd
   * @return
   */
  public static double generateRandomValuesInRange(double outputStart, double outputEnd) {
    return mapRange(Math.random(), 0, 1, outputStart, outputEnd);
  }

  public static Optional<List<String>> readNames() {

    ClassLoader classLoader = GameUtility.class.getClassLoader();

    try(Scanner readNames = new Scanner(
        new File(classLoader.getResource("AwesomeNames.txt").getFile()));) {

      List<String> names = new ArrayList<>();
      while (readNames.hasNextLine()) {
        names.add(readNames.nextLine());
      }
      return Optional.of(names);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  /**
   * Generates smooth random numbers which are relatively closer to each other.
   * @param count
   * @param noise
   * @param octaves
   * @return
   */
  public double[] perlinNoise1D(int count, double[] noise, int octaves) {
    double output[] = new double[count];
    for (int x = 0; x < count; x++) {
      double dNoise = 0;
      double fScale = 1;
      double fScaleAcc = 0;
      for (int oct = 0; oct < octaves; oct++) {
        int nPitch = count >> oct;
        int nSample1 = (x / nPitch) * nPitch;
        int nSample2 = (nSample1 + nPitch) % count;
        double fBlend = (double) (x - nSample1) / (double) nPitch;
        double fSample = (1 - fBlend) * noise[nSample1] + fBlend * noise[nSample2];
        dNoise += fSample * fScale;
        fScaleAcc += fScale;
        fScale /= 2;
      }
      output[x] = dNoise / fScaleAcc;
    }
    return (output);
  }
}
