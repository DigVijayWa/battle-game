package objects.enemy;

import objects.GameObject;
import objects.GameObjectType;

public abstract class Enemy extends GameObject {

  public Enemy(int totalVertices, boolean movable) {
    super(totalVertices, movable, GameObjectType.ENEMY);
  }
}
