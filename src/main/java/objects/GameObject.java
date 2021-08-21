package objects;

import events.GameEvent;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;
import physics.Vector;
import utility.GameUtility;

public abstract class GameObject implements Serializable {

  Vector position;
  Vector acceleration;
  Vector friction;
  Vector velocity;

  Vector gravity;

  Vector[] vertices;

  Path2D objectPath = new Double();

  int size;

  boolean movable;

  UUID id;

  GameObjectType gameObjectType;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public GameObject(int totalVertices, GameObjectType gameObjectType) {

    position = new Vector(GameUtility.generateRandomValuesInRange(10, 800),
        GameUtility.generateRandomValuesInRange(10, 800));

    id = UUID.randomUUID();

    acceleration = new Vector(0, 0);
    velocity = new Vector(0, 0);
    gravity = new Vector(0, 5);
    friction = new Vector(1, 1);
    size = 2;
    this.vertices = new Vector[totalVertices];
    this.gameObjectType = gameObjectType;
  }

  public GameObject(int totalVertices, boolean movable, GameObjectType gameObjectType) {

    position = new Vector(GameUtility.generateRandomValuesInRange(10, 800),
        GameUtility.generateRandomValuesInRange(10, 800));

    this.movable = movable;

    id = UUID.randomUUID();

    acceleration = new Vector(0, 0);
    velocity = new Vector(0, 0);
    gravity = new Vector(0, 5);
    friction = new Vector(1, 1);
    size = 2;
    this.vertices = new Vector[totalVertices];
    this.gameObjectType = gameObjectType;
  }

  public GameObject(Vector position, Vector acceleration, Vector velocity, Vector gravity, int size,
      boolean movable, int totalVertices, GameObjectType gameObjectType) {
    id = UUID.randomUUID();
    this.position = position;
    this.acceleration = acceleration;
    this.velocity = velocity;
    this.gravity = gravity;
    this.size = size;
    this.movable = movable;
    this.vertices = new Vector[totalVertices];
    this.gameObjectType = gameObjectType;
  }

  public GameObject(Vector position, Vector acceleration, Vector velocity, int size,
      boolean movable, GameObjectType gameObjectType) {
    id = UUID.randomUUID();
    this.position = position;
    this.acceleration = acceleration;
    this.velocity = velocity;
    this.size = size;
    this.movable = movable;
    this.gameObjectType = gameObjectType;
  }

  public abstract void render(Graphics2D g);

  public abstract void update(double timePassed);

  public abstract void applyInput(GameEvent event);

  public abstract void removeInput(GameEvent event);

  public abstract boolean shouldBeRemoved();

  public abstract void handleCollision(GameEvent gameEvent);

  public void updatePath(Vector velocity, double timePassed) {
    vertices = Arrays.stream(vertices).map(item -> item.additionVector(velocity, timePassed))
        .toArray(Vector[]::new);
  }

  public void calculatePath() {
    objectPath.moveTo(vertices[0].getX(), vertices[0].getY());

    IntStream.rangeClosed(1, vertices.length - 1)
        .forEach(index -> objectPath.lineTo(vertices[index].getX(), vertices[index].getY()));

    objectPath.closePath();
  }


  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public Vector getAcceleration() {
    return acceleration;
  }

  public void setAcceleration(Vector acceleration) {
    this.acceleration = acceleration;
  }

  public Vector getFriction() {
    return friction;
  }

  public void setFriction(Vector friction) {
    this.friction = friction;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public Vector getGravity() {
    return gravity;
  }

  public void setGravity(Vector gravity) {
    this.gravity = gravity;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public boolean isMovable() {
    return movable;
  }

  public void setMovable(boolean movable) {
    this.movable = movable;
  }

  public Vector[] getVertices() {
    return vertices;
  }

  public void setVertices(Vector[] vertices) {
    this.vertices = vertices;
  }

  public Path2D getObjectPath() {
    return objectPath;
  }

  public void setObjectPath(Path2D objectPath) {
    this.objectPath = objectPath;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  public GameObjectType getGameObjectType() {
    return gameObjectType;
  }

  public void setGameObjectType(GameObjectType gameObjectType) {
    this.gameObjectType = gameObjectType;
  }
}
