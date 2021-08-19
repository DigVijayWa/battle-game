package objects;

import events.GameEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.stream.IntStream;
import physics.Vector;

public class Block extends GameObject {

  public Block() {
    super(5);
    double[] noise = IntStream.rangeClosed(0, 4).mapToDouble(index -> Math.random()).toArray();
    noise = perlinNoise1D(5, noise, 2);

    vertices[0] = new Vector(300, 300);
    for (int index=1; index<=4; index++) {
      vertices[index] = vertices[index-1].additionVector(new Vector(0, 0)
          .setAngleAndMagnitude((noise[index] * Math.PI * 2),
              (20)));
      System.out.println(
          "X : " + vertices[index].getX() + " Y : " + vertices[index].getY() + " noise "
              + noise[index]);
    }

    calculatePath();
  }

  public Block(Vector position, Vector acceleration, Vector velocity,
      Vector gravity, int size, boolean movable, int totalVertices) {
    super(position, acceleration, velocity, gravity, size, movable, totalVertices);
  }

  public Block(Vector position, Vector acceleration, Vector velocity, int size, boolean movable) {
    super(position, acceleration, velocity, size, movable);
  }

  @Override
  public void render(Graphics2D g) {

    Color prevColor = g.getColor();
    g.setColor(Color.WHITE);

    g.draw(objectPath);

    g.setColor(prevColor);
  }

  @Override
  public void update(double timePassed) {

  }

  @Override
  public void applyInput(GameEvent event) {

  }

  @Override
  public void removeInput(GameEvent event) {

  }

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
