package events;

import java.util.UUID;

public class CollisionEvent extends GameEvent{

  private UUID objectId2;

  public CollisionEvent(int priority, EventType eventType, UUID objectId) {
    super(priority, eventType, objectId);
  }

  public CollisionEvent(int priority, EventType eventType, UUID objectId1, UUID objectId2) {
    super(priority, eventType, objectId1);
    this.objectId2 = objectId2;
  }

  public UUID getObjectId2() {
    return objectId2;
  }

  public void setObjectId2(UUID objectId2) {
    this.objectId2 = objectId2;
  }
}
