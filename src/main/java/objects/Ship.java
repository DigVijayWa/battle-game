package objects;

import events.GameEvent;
import game.context.GameContext;
import game.input.KeyInput.KeyType;
import game.input.event.KeyInputEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import physics.Vector;
import utility.GameUtility;

public class Ship extends GameObject {

  double timePassedSeconds = 0.01666666666;

  public static double maxSpeed = 200;

  public static double maxForce = 16;

  public static double attractionDistance = 100;

  public static double minForce = 80;

  private int eatenParticles = 0;

  private double differential = Math.PI / 24;

  private String name;

  private boolean destroyed;

  private Map<KeyType, Boolean> pressedKeys = new HashMap<>();

  public Ship() {
    super(3, true, GameObjectType.SHIP);
    double offSet = GameUtility.mapRandomValue(Math.random());
    vertices[0] = new Vector(offSet, offSet);
    vertices[1] = new Vector(5 + offSet, 19.319 + offSet);
    vertices[2] = new Vector(10 + offSet, offSet);

    position = calculateCentroid();

    calculatePath();

  }

  public Ship(Vector position, Vector acceleration, Vector velocity, Vector gravity, int size,
      boolean movable) {
    super(position, acceleration, velocity, gravity, size, movable, 3, GameObjectType.SHIP);
  }

  public Ship(Vector position, Vector acceleration, Vector velocity, int size, boolean movable) {
    super(position, acceleration, velocity, size, movable, GameObjectType.SHIP);
  }

  @Override
  public void render(Graphics2D graphics2D) {
    Color prevColor = graphics2D.getColor();
    graphics2D.setColor(Color.WHITE);
    graphics2D.setFont(new Font("Monospace", Font.PLAIN, 12));
    graphics2D
        .drawString("Name " + GameContext.getActiveObjects().size(), (int) vertices[1].getX() + 3,
            (int) vertices[1].getY() + 3);
    if (velocity.getMagnitude() != 0) {
      graphics2D.rotate(velocity.getAngle() - Math.PI / 2,
          position.getX(), position.getY());
    }
    graphics2D.draw(objectPath);

    graphics2D.drawOval((int) position.getX(), (int) position.getY(), 2, 2);

    if (velocity.getMagnitude() != 0) {
      graphics2D.rotate(-(velocity.getAngle() - Math.PI / 2), position.getX(), position.getY());
    }

    graphics2D.setColor(prevColor);
    objectPath.reset();
  }

  @Override
  public void update(double timePassed) {
    if (movable) {

      pressedKeys.entrySet().stream().filter(Entry::getValue)
          .forEach(item -> applyInputAccordingToState(item.getKey()));

      velocity = velocity.additionVector(this.acceleration);
      velocity = velocity.limitVector(maxSpeed);

      updatePath(velocity, timePassedSeconds);

      calculatePath();

      position = calculateCentroid();

      acceleration.setXandY(0, 0);
    }
  }

  @Override
  public void applyInput(GameEvent event) {
    KeyInputEvent keyInputEvent = (KeyInputEvent) event;

    pressedKeys
        .put(keyInputEvent.getKeyInput().getKeyType(), keyInputEvent.getKeyInput().isHeldDown());
  }

  @Override
  public void removeInput(GameEvent event) {
    KeyInputEvent keyInputEvent = (KeyInputEvent) event;

    pressedKeys
        .put(keyInputEvent.getKeyInput().getKeyType(), keyInputEvent.getKeyInput().isHeldDown());

    if (keyInputEvent.getKeyInput().getKeyType() == KeyType.FIRE) {
      List<GameObject> gameObjects = GameContext.getActiveObjects();
      gameObjects.add(new Laser(position, velocity, this.id));
      GameContext.setGameObjects(gameObjects);
    }
  }

  @Override
  public boolean shouldBeRemoved() {
    return destroyed;
  }

  @Override
  public void handleCollision(GameEvent gameEvent) {
    this.destroyed = true;
  }

  public Vector calculateCentroid() {

    return Arrays.stream(vertices).reduce(new Vector(0, 0), (item, acc) -> acc.additionVector(item))
        .divideByScalar(3);
  }

  public void applyInputAccordingToState(KeyType keyType) {
    switch (keyType) {
      case ACCELERATE:
        this.addAcceleration(
            new Vector(0, 0).setAngleAndMagnitude(this.velocity.getAngle(), maxSpeed));
        break;
      case DECELERATE:
        this.addAcceleration(
            new Vector(0, 0).setAngleAndMagnitude(this.velocity.getAngle(), -maxSpeed));
        break;
      case RIGHT_ROTATE:
        this.velocity.setAngleAndMagnitude(this.velocity.getAngle() - differential,
            this.velocity.getMagnitude());
        break;
      case LEFT_ROTATE:
        this.velocity.setAngleAndMagnitude(this.velocity.getAngle() + differential,
            this.velocity.getMagnitude());
        break;
      default:
    }
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

  public Vector[] getVertices() {
    return vertices;
  }

  public void setVertices(Vector[] vertices) {
    this.vertices = vertices;
  }

  public int getEatenParticles() {
    return eatenParticles;
  }

  public int setEatenParticles(int eatenParticles) {
    this.eatenParticles = eatenParticles;
    return this.eatenParticles;
  }

  public void addAcceleration(Vector deltaAcceleration) {
    this.acceleration = this.acceleration.additionVector(deltaAcceleration, timePassedSeconds);
    this.acceleration = this.acceleration.limitVector(maxForce);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
