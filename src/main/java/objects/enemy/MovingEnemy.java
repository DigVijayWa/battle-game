package objects.enemy;

import events.GameEvent;
import game.context.GameContext;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import objects.GameObject;
import objects.GameObjectType;
import physics.Vector;
import utility.GameUtility;

public class MovingEnemy extends Enemy {

  public static double maxForce = 16;

  public static double minSpeed = 25;

  public static double maxSpeed = 75;

  double timePassedSeconds = 0.01666666666;

  public MovingEnemy() {
    super(3, true);
    double offSet = GameUtility.mapRandomValue(Math.random());
    vertices[0] = new Vector(offSet, offSet);
    vertices[1] = new Vector(5 + offSet, 19.319 + offSet);
    vertices[2] = new Vector(10 + offSet, offSet);

    this.setPosition(calculateCentroid());

    calculatePath();
  }

  @Override
  public void render(Graphics2D graphics2D) {
    Color prevColor = graphics2D.getColor();
    graphics2D.setColor(Color.WHITE);
    if (this.getVelocity().getMagnitude() != 0) {
      graphics2D.rotate(this.getVelocity().getAngle() - Math.PI / 2,
          this.getPosition().getX(), this.getPosition().getY());
    }
    graphics2D.draw(this.getObjectPath());

    if (this.getVelocity().getMagnitude() != 0) {
      graphics2D.rotate(-(this.getVelocity().getAngle() - Math.PI / 2), this.getPosition().getX(),
          this.getPosition().getY());
    }

    graphics2D.setColor(prevColor);
    this.getObjectPath().reset();
  }

  @Override
  public void update(double timePassed) {
    List<Vector> shipVectors = getShipPositions();

    if (!shipVectors.isEmpty()) {
      Vector shipVector = shipVectors.get(0);

      double scalar = GameUtility
          .mapRange(this.getPosition().distance(shipVector),
              0, 800, minSpeed, maxSpeed);

      Vector acceleration = GameUtility.seek(this.getPosition(), shipVector,
          this.getVelocity(), scalar);

      this.setAcceleration(acceleration);
    }

    this.setVelocity(this.getVelocity().additionVector(this.getAcceleration()));
    this.setVelocity(this.getVelocity().limitVector(maxSpeed));

    updatePath(this.getVelocity(), timePassedSeconds);

    calculatePath();

    this.setPosition(calculateCentroid());

    this.getAcceleration().setXandY(0, 0);
  }

  @Override
  public void applyInput(GameEvent event) {

  }

  @Override
  public void removeInput(GameEvent event) {

  }

  @Override
  public boolean shouldBeRemoved() {
    return false;
  }

  @Override
  public void handleCollision(GameEvent gameEvent) {

  }

  public Vector calculateCentroid() {

    return Arrays.stream(vertices).reduce(new Vector(0, 0), (item, acc) -> acc.additionVector(item))
        .divideByScalar(3);
  }

  private List<Vector> getShipPositions() {
    List<GameObject> gameObjects = GameContext.getActiveObjects().stream()
        .filter(gameObject -> gameObject.getGameObjectType() == GameObjectType.SHIP).collect(
            Collectors.toList());

    return gameObjects.stream().map(GameObject::getPosition)
        .collect(Collectors.toList());
  }

  private List<Vector> getOtherObjects() {
    List<GameObject> gameObjects = GameContext.getActiveObjects().stream()
        .filter(gameObject -> gameObject.getGameObjectType() != GameObjectType.SHIP
            && gameObject.getGameObjectType() != GameObjectType.LASER
            && gameObject.getGameObjectType() != GameObjectType.ENEMY).collect(
            Collectors.toList());

    return gameObjects.stream().map(GameObject::getPosition)
        .collect(Collectors.toList());
  }
}
