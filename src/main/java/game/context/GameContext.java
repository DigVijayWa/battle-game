package game.context;

import events.CollisionEvent;
import events.EventBus;
import events.EventHandler;
import events.EventType;
import events.ItemPickedEvent;
import game.input.event.KeyInputEvent;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import objects.Asteroid;
import objects.GameObject;
import objects.GameObjectType;
import objects.Ship;
import objects.items.ItemType;
import utility.GameUtility;

public class GameContext implements Context {

  private ContextType contextType;

  private static List<GameObject> gameObjects;

  public GameContext() {
    this.contextType = ContextType.GAME;
    gameObjects = new LinkedList<>();
    gameObjects.add(new Ship());

    for (int i = 0; i < 5; i++) {
      gameObjects.add(new Asteroid((int) GameUtility.generateRandomValuesInRange(4, 10)));
    }

    EventBus.subscribe(EventType.KEY_PRESSED, keyPressedEventHandler);
    EventBus.subscribe(EventType.KEY_RELEASED, keyReleasedEventHandler);
    EventBus.subscribe(EventType.COLLISION, collisionEventHandler);
  }

  @Override
  public synchronized void render(Graphics2D g) {
    for (int i = gameObjects.size() - 1; i >= 0; i--) {
      gameObjects.get(i).render(g);
    }
  }

  @Override
  public synchronized void update(double timePassed) {
    gameObjects.forEach(gameObject -> gameObject.update(timePassed));
    removeObjects();
    checkForCollisionsWithLaser();
    checkForCollisions();
  }


  public EventHandler keyPressedEventHandler = event -> {

    KeyInputEvent keyEvent = (KeyInputEvent) event;

    for (GameObject gameObject : gameObjects) {
      if (gameObject.getId().compareTo(event.getObjectId()) == 0) {
        gameObject.applyInput(keyEvent);
        break;
      }
    }
  };

  public EventHandler keyReleasedEventHandler = event -> {
    KeyInputEvent keyEvent = (KeyInputEvent) event;
    for (GameObject gameObject : gameObjects) {
      if (gameObject.getId().compareTo(event.getObjectId()) == 0) {
        gameObject.removeInput(keyEvent);
        break;
      }
    }
  };

  public EventHandler collisionEventHandler = event -> {

    CollisionEvent collisionEvent = (CollisionEvent) event;

    UUID uuid1 = collisionEvent.getObjectId();
    UUID uuid2 = collisionEvent.getObjectId2();

    for (GameObject gameObject : gameObjects) {
      if (gameObject.getId().compareTo(uuid1) == 0
          || gameObject.getId().compareTo(uuid2) == 0) {
        gameObject.handleCollision(collisionEvent);
      }
    }
  };


  public void checkForCollisions() {
    List<GameObject> shipObjectList = gameObjects.stream()
        .filter(item -> item.getGameObjectType() == GameObjectType.SHIP)
        .collect(Collectors.toList());

    if (!shipObjectList.isEmpty()) {

      Ship shipObject = (Ship) shipObjectList.get(0);

      List<GameObject> otherObjects = gameObjects.stream()
          .filter(item -> item.getGameObjectType() != GameObjectType.LASER
              && item.getGameObjectType() != GameObjectType.SHIP)
          .collect(Collectors.toList());

      for (GameObject gameObject : otherObjects) {
        if (shipObject.getObjectPath().getBounds2D()
            .intersects(gameObject.getObjectPath().getBounds2D())) {
          EventBus.publish(new CollisionEvent(1, EventType.COLLISION, shipObject.getId(),
              gameObject.getId()));
        }
      }
    }
  }

  public void checkForCollisionsWithLaser() {
    List<GameObject> lasers = gameObjects.stream()
        .filter(item -> item.getGameObjectType() == GameObjectType.LASER)
        .collect(Collectors.toList());

    List<GameObject> otherGameObjects = gameObjects.stream()
        .filter(item -> item.getGameObjectType() != GameObjectType.LASER
            && item.getGameObjectType() != GameObjectType.SHIP)
        .collect(Collectors.toList());

    for (GameObject gameObject : otherGameObjects) {
      for (GameObject laser : lasers) {
        if (laser.getObjectPath().getBounds2D()
            .intersects(gameObject.getObjectPath().getBounds2D())) {
          EventBus.publish(new CollisionEvent(1, EventType.COLLISION, laser.getId(),
              gameObject.getId()));
        }
      }
    }
  }

  public void handleObjects(GameObject shipObject, GameObject otherObject) {
    switch (otherObject.getGameObjectType()) {
      case SHIP:
        break;
      case SPECIAL_ITEM:
        EventBus.publish(
            new ItemPickedEvent(1, EventType.ITEM_PICKED, shipObject.getId(), otherObject.getId(),
                ItemType.SPEED_BOOSTER));
        break;
      case ASTEROID:
      case BLOCK:
      case LASER:
        EventBus.publish(new CollisionEvent(1, EventType.COLLISION, shipObject.getId(),
            otherObject.getId()));
        break;
      default:
    }
  }

  public synchronized void removeObjects() {
    gameObjects = gameObjects.stream().filter(item -> !item.shouldBeRemoved())
        .collect(Collectors.toList());
  }

  public static List<GameObject> getActiveObjects() {
    return gameObjects;
  }

  public ContextType getContextType() {
    return contextType;
  }

  public void setContextType(ContextType contextType) {
    this.contextType = contextType;
  }


  public static void setGameObjects(List<GameObject> gameObjects) {
    GameContext.gameObjects = gameObjects;
  }
}
