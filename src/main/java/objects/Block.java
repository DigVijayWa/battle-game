package objects;

import events.GameEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import physics.Vector;

public class Block extends GameObject {

  public Block() {
    super(4, false, GameObjectType.BLOCK);
    vertices[0] = new Vector(position.getX(), position.getY()-25);
    vertices[1] = new Vector(position.getX(), position.getY()+25);
    vertices[2] = new Vector(position.getX() + 100, position.getY()+25);
    vertices[3] = new Vector(position.getX() + 100, position.getY()-25);

    calculatePath();
    this.position = calculateCentroid();
  }

  public Block(int x, int y) {
    super(4, false, GameObjectType.BLOCK);
    position = new Vector(x, y);
    vertices[0] = new Vector(position.getX(), position.getY());
    vertices[1] = new Vector(position.getX(), position.getY()+10);
    vertices[2] = new Vector(position.getX() + 10, position.getY()+10);
    vertices[3] = new Vector(position.getX() + 10, position.getY());

    calculatePath();
    //this.position = calculateCentroid();
  }

  public Block(Vector position, Vector acceleration, Vector velocity,
      Vector gravity, int size, boolean movable, int totalVertices) {
    super(position, acceleration, velocity, gravity, size, movable, totalVertices, GameObjectType.BLOCK);
  }

  public Block(Vector position, Vector acceleration, Vector velocity, int size, boolean movable) {
    super(position, acceleration, velocity, size, movable, GameObjectType.BLOCK);
  }

  @Override
  public void render(Graphics2D g) {

    Color prevColor = g.getColor();
    g.setColor(Color.WHITE);
    //g.translate(position.getX(), position.getY());
    g.draw(objectPath);
    //g.translate(-position.getX(), -position.getY());

    g.setColor(prevColor);
  }

  @Override
  public void update(double timePassed) {

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
    return new Vector((vertices[0].getX() + 5), vertices[0].getY() + 5);
  }
}
