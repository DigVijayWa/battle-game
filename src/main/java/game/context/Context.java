package game.context;

import java.awt.Graphics2D;
import java.util.List;
import objects.GameObject;

public interface Context {

  void render(Graphics2D g);

  void update(double timePassed);
}
