package events;

import java.util.UUID;
import objects.items.ItemType;

public class ItemPickedEvent extends GameEvent{

  private ItemType itemType;

  private UUID itemId;

  public ItemPickedEvent(int priority, EventType eventType, UUID objectId, UUID itemId, ItemType itemType) {
    super(priority, eventType, objectId);
    this.itemType = itemType;
    this.itemId = itemId;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  public UUID getItemId() {
    return itemId;
  }

  public void setItemId(UUID itemId) {
    this.itemId = itemId;
  }
}
