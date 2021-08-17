package game.input.event;

import events.EventType;
import events.GameEvent;
import game.context.GameContext;

public class NetworkInputEvent extends GameEvent {

  public NetworkInputEvent(int priority, EventType eventType) {
    super(priority, eventType, GameContext.getActiveObjects().get(0).getId());
  }
}
