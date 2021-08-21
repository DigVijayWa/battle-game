package game.context;

import java.awt.Graphics2D;

public interface Context {

  void render(Graphics2D g);

  void update(double timePassed);
}
