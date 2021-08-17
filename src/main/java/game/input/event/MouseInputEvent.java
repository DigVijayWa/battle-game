package game.input.event;

import events.EventType;
import events.GameEvent;
import game.context.GameContext;
import game.input.MouseInput;

public class MouseInputEvent extends GameEvent {

  private MouseInput mouseInput;

  public MouseInputEvent(int priority, EventType eventType) {
    super(priority, eventType, GameContext.getActiveObjects().get(0).getId());
  }

  public MouseInputEvent(int priority, EventType eventType, MouseInput mouseInput) {
    super(priority, eventType, GameContext.getActiveObjects().get(0).getId());
    this.mouseInput = mouseInput;
  }

  public MouseInput getMouseInput() {
    return mouseInput;
  }

  public void setMouseInput(MouseInput mouseInput) {
    this.mouseInput = mouseInput;
  }
}
