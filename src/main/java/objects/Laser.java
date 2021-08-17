package objects;

import events.GameEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import physics.Vector;

public class Laser extends GameObject {

  public double maxSpeed = 400;

  public double maxForce = 16;

  public Laser(Vector position, Vector velocity) {
    super(4, true);
    vertices[0] = new Vector(position.getX(), position.getY()-1);
    vertices[1] = new Vector(position.getX(), position.getY()+1);
    vertices[2] = new Vector(position.getX() + 10, position.getY()+1);
    vertices[3] = new Vector(position.getX() + 10, position.getY()-1);

    this.velocity = new Vector(0, 0).setAngleAndMagnitude(velocity.getAngle(), maxSpeed);
    calculatePath();
    this.position = calculateCentroid();
  }

  public Laser(Vector position, Vector acceleration, Vector velocity,
      Vector gravity, int size, boolean movable, int totalVertices) {
    super(position, acceleration, velocity, gravity, size, movable, totalVertices);
  }

  public Laser(Vector position, Vector acceleration, Vector velocity, int size, boolean movable) {
    super(position, acceleration, velocity, size, movable);
  }

  @Override
  public void render(Graphics2D g) {
    Color prevColor = g.getColor();
    g.setColor(Color.WHITE);
    if (velocity.getMagnitude() != 0) {
      g.rotate(velocity.getAngle(),
          position.getX(), position.getY());
    }
    g.draw(objectPath);
    if (velocity.getMagnitude() != 0) {
      g.rotate(-(velocity.getAngle()), position.getX(), position.getY());
    }
    g.setColor(prevColor);
    objectPath.reset();
  }

  @Override
  public void update(double timePassed) {
    if (movable) {
      double timePassedSeconds = 0.01666666666;

      updatePath(velocity, timePassedSeconds);
      calculatePath();
      position = calculateCentroid();
    }
  }

  @Override
  public void applyInput(GameEvent event) {}

  public boolean shouldBeRemoved() {
    return !(this.position.getX() > 800 + 10 ||
    this.position.getX() < 0 - 10 ||
    this.position.getY() > 800 + 10 ||
    this.position.getY() < 0 - 10);
  }

  public Vector calculateCentroid() {
    return new Vector((vertices[0].getX() + 5), vertices[0].getY() + 1);
  }
}
