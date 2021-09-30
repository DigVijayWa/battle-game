package objects;

import events.GameEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.UUID;
import physics.Vector;

public class Laser extends GameObject {

  private UUID shipId;

  public double maxSpeed = 800;

  public double maxForce = 16;

  boolean destroyed = false;

  public Laser(Vector position, Vector velocity, UUID shipId) {
    super(4, true, GameObjectType.LASER);
    vertices[0] = new Vector(position.getX()+5, position.getY() - 1);
    vertices[1] = new Vector(position.getX()-5, position.getY() + 1);
    vertices[2] = new Vector(position.getX() + 5, position.getY() + 1);
    vertices[3] = new Vector(position.getX() - 5, position.getY() - 1);

    this.shipId = shipId;

    this.velocity = new Vector(0, 0).setAngleAndMagnitude(velocity.getAngle(), maxSpeed);
    calculatePath();
    this.position = calculateCentroid();
  }

  public Laser(Vector position, Vector acceleration, Vector velocity,
      Vector gravity, int size, boolean movable, int totalVertices) {
    super(position, acceleration, velocity, gravity, size, movable, totalVertices,
        GameObjectType.LASER);
  }

  public Laser(Vector position, Vector acceleration, Vector velocity, int size, boolean movable) {
    super(position, acceleration, velocity, size, movable, GameObjectType.LASER);
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
  public void applyInput(GameEvent event) {
  }

  @Override
  public void removeInput(GameEvent event) {

  }

  @Override
  public boolean shouldBeRemoved() {
    return (this.position.getX() > 800 + 10 ||
        this.position.getX() < 0 - 10 ||
        this.position.getY() > 800 + 10 ||
        this.position.getY() < 0 - 10) || destroyed;
  }

  @Override
  public void handleCollision(GameEvent gameEvent) {
    this.destroyed = true;
  }

  public Vector calculateCentroid() {
    return new Vector((vertices[0].getX() + 5), vertices[0].getY() + 1);
  }

  public UUID getShipId() {
    return shipId;
  }

  public void setShipId(UUID shipId) {
    this.shipId = shipId;
  }

  public double getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public double getMaxForce() {
    return maxForce;
  }

  public void setMaxForce(double maxForce) {
    this.maxForce = maxForce;
  }

  public boolean isDestroyed() {
    return destroyed;
  }

  public void setDestroyed(boolean destroyed) {
    this.destroyed = destroyed;
  }
}
