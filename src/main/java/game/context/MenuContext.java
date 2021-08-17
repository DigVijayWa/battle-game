package game.context;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import objects.GameObject;

public class MenuContext implements Context {

  private ContextType contextType;

  private static List<GameObject> gameObjects;

  public MenuContext() {
    this.contextType = ContextType.MENU;
    this.gameObjects = new ArrayList<>();
  }

  @Override
  public void render(Graphics2D g) {
    gameObjects.stream().forEach(gameObject -> gameObject.render(g));
  }

  @Override
  public void update(double timePassed) {
    gameObjects.stream().forEach(gameObject -> gameObject.update(timePassed));
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
}
