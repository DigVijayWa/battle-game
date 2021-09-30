package objects.enemy;

import events.GameEvent;
import game.context.GameContext;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import objects.GameObject;
import objects.GameObjectType;
import objects.Laser;
import physics.Vector;
import utility.GameUtility;

public class StationaryEnemy extends Enemy {

  double timeDelay = 2300;

  long lastTimeLaserSent = new Date().getTime();

  public StationaryEnemy() {
    super(4, false);
    vertices[0] = new Vector(this.getPosition().getX(), this.getPosition().getY() - 10);
    vertices[1] = new Vector(this.getPosition().getX(), this.getPosition().getY() + 10);
    vertices[2] = new Vector(this.getPosition().getX() + 20, this.getPosition().getY() + 10);
    vertices[3] = new Vector(this.getPosition().getX() + 20, this.getPosition().getY() - 10);

    calculatePath();
    this.setPosition(calculateCentroid());
  }

  @Override
  public void render(Graphics2D g) {
    Color prevColor = g.getColor();
    g.setColor(Color.WHITE);
    g.draw(this.getObjectPath());
    g.setColor(prevColor);
  }

  @Override
  public void update(double timePassed) {
    long timeNow = new Date().getTime();

    if (timeNow - lastTimeLaserSent > timeDelay) {
      List<GameObject> gameObjects = GameContext.getActiveObjects();
      List<Vector> shipVectors = seekShip();
      shipVectors.forEach(shipVector -> {
        gameObjects.add(new Laser(this.getPosition(), shipVector, this.getId()));
      });
      GameContext.setGameObjects(gameObjects);
      lastTimeLaserSent = new Date().getTime();
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
    return false;
  }

  @Override
  public void handleCollision(GameEvent gameEvent) {

  }

  private List<Vector> seekShip() {
    List<GameObject> gameObjects = GameContext.getActiveObjects().stream()
        .filter(gameObject -> gameObject.getGameObjectType() == GameObjectType.SHIP).collect(
            Collectors.toList());

    return gameObjects.stream().map(gameObject -> GameUtility
        .calculateEffectiveVector(this.getPosition(), gameObject.getPosition()))
        .collect(Collectors.toList());
  }

  public Vector calculateCentroid() {
    return new Vector((vertices[0].getX() + 10), vertices[0].getY() + 10);
  }
}
