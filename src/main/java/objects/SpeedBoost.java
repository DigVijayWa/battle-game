package objects;

import events.GameEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import objects.items.ItemType;
import physics.Vector;

public class SpeedBoost extends GameObject {

  private boolean destroyed = false;

  ItemType itemType = ItemType.SPEED_BOOSTER;

  public SpeedBoost() {
    super(4, true, GameObjectType.SPECIAL_ITEM);
    vertices[0] = new Vector(position.getX(), position.getY() - 10);
    vertices[1] = new Vector(position.getX(), position.getY() + 10);
    vertices[2] = new Vector(position.getX() + 20, position.getY() + 10);
    vertices[3] = new Vector(position.getX() + 20, position.getY() - 10);

    calculatePath();
    this.position = calculateCentroid();
  }

  @Override
  public void render(Graphics2D g) {
    Color prevColor = g.getColor();
    g.setColor(Color.WHITE);
    g.draw(objectPath);

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
    return destroyed;
  }

  @Override
  public void handleCollision(GameEvent gameEvent) {
    this.destroyed = true;
  }

  public Vector calculateCentroid() {
    return new Vector((vertices[0].getX() + 10), vertices[0].getY() + 10);
  }
}
