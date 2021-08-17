package game.input.event;

import events.EventType;
import events.GameEvent;
import game.context.GameContext;
import game.input.KeyInput;

public class KeyInputEvent extends GameEvent {

  private KeyInput keyInput;

  public KeyInputEvent(int priority, EventType eventType) {
    super(priority, eventType, GameContext.getActiveObjects().get(0).getId());
  }

  public KeyInputEvent(int priority, EventType eventType, KeyInput keyInput) {
    super(priority, eventType, GameContext.getActiveObjects().get(0).getId());
    this.keyInput = keyInput;
  }

  public KeyInput getKeyInput() {
    return keyInput;
  }

  public void setKeyInput(KeyInput keyInput) {
    this.keyInput = keyInput;
  }
}
