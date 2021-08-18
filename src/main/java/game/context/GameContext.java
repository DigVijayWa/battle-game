package game.context;

import events.EventBus;
import events.EventHandler;
import events.EventType;
import game.input.event.KeyInputEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import objects.GameObject;
import objects.Ship;

public class GameContext implements Context {

  private ContextType contextType;

  private static List<GameObject> gameObjects;

  public GameContext() {
    this.contextType = ContextType.GAME;
    gameObjects = new ArrayList<>();
    gameObjects.add(new Ship());
    EventBus.subscribe(EventType.KEY_PRESSED, keyPressedEventHandler);
    EventBus.subscribe(EventType.KEY_RELEASED, keyReleasedEventHandler);
  }

  @Override
  public void render(Graphics2D g) {
    gameObjects.forEach(gameObject -> gameObject.render(g));
  }

  @Override
  public void update(double timePassed) {
    gameObjects.forEach(gameObject -> gameObject.update(timePassed));
  }


  public EventHandler keyPressedEventHandler = event -> {

    KeyInputEvent keyEvent = (KeyInputEvent) event;
    gameObjects.forEach(gameObject -> {
      if(gameObject.getId().compareTo(event.getObjectId()) == 0) {
        gameObject.applyInput(keyEvent);
      }
    });
  };

  public EventHandler keyReleasedEventHandler = event -> {
    KeyInputEvent keyEvent = (KeyInputEvent) event;
    gameObjects.forEach(gameObject -> {
      if(gameObject.getId().compareTo(event.getObjectId()) == 0) {
        gameObject.removeInput(keyEvent);
      }
    });
  };

  public static List<GameObject> getActiveObjects() {
    return gameObjects;
  }

  public ContextType getContextType() {
    return contextType;
  }

  public void setContextType(ContextType contextType) {
    this.contextType = contextType;
  }
}
