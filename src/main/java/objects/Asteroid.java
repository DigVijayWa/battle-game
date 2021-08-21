package objects;

import events.GameEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.stream.IntStream;
import physics.Vector;
import utility.GameUtility;

public class Asteroid extends GameObject {


  private int health;

  private double noise[];

  public Asteroid(int totalVertices) {
    super((int) GameUtility.generateRandomValuesInRange(4, 16), GameObjectType.ASTEROID);
    noise = IntStream.rangeClosed(0, this.vertices.length)
        .mapToDouble(index -> Math.random()).toArray();

    this.health = vertices.length;

    for (int index = 0; index < this.vertices.length; index++) {

      double angle = GameUtility.mapRange(index, 0, this.vertices.length, 0, Math.PI * 2);
      double magnitude = GameUtility.mapRange(noise[index], 0, 1, 10, 20);

      vertices[index] = new Vector(0, 0)
          .setAngleAndMagnitude(angle,
              (magnitude)).additionVector(position);
    }

    calculatePath();
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

  @Override
  public boolean shouldBeRemoved() {
    return this.health <= 0;
  }

  @Override
  public void handleCollision(GameEvent gameEvent) {
    this.health = this.health - 1;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
