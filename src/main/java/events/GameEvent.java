package events;

import java.util.UUID;

public class GameEvent {

  private UUID eventId;

  private UUID objectId;

  private int priority;

  private EventType eventType;

  public GameEvent(int priority, EventType eventType, UUID objectId) {
    this.eventId = UUID.randomUUID();
    this.priority = priority;
    this.eventType = eventType;
    this.objectId = objectId;
  }

  public UUID getObjectId() {
    return objectId;
  }

  public void setObjectId(UUID objectId) {
    this.objectId = objectId;
  }

  public UUID getEventId() {
    return eventId;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
}
