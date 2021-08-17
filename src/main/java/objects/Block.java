package objects;

import events.GameEvent;
import java.awt.Graphics2D;
import physics.Vector;

public class Block extends GameObject {

  public Block(int totalVertices) {
    super(totalVertices);
  }

  public Block(Vector position, Vector acceleration, Vector velocity,
      Vector gravity, int size, boolean movable, int totalVertices) {
    super(position, acceleration, velocity, gravity, size, movable, totalVertices);
  }

  public Block(Vector position, Vector acceleration, Vector velocity, int size, boolean movable) {
    super(position, acceleration, velocity, size, movable);
  }

  @Override
  public void render(Graphics2D g) {

  }

  @Override
  public void update(double timePassed) {

  }

  @Override
  public void applyInput(GameEvent event) {

  }
}
