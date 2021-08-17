package game.input.listener;

import events.EventBus;
import events.EventType;
import game.input.KeyInput;
import game.input.KeyInput.KeyType;
import game.input.event.KeyInputEvent;
import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
            new KeyInput(e.getKeyCode(), false, KeyType.ACCELERATE)));
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
            new KeyInput(e.getKeyCode(), false, KeyType.LEFT_ROTATE)));
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
            new KeyInput(e.getKeyCode(), false, KeyType.DECELERATE)));
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
            new KeyInput(e.getKeyCode(), false, KeyType.RIGHT_ROTATE)));
        break;
      default:
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_SPACE:
        EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
            new KeyInput(e.getKeyCode(), false, KeyType.FIRE)));
        break;
      default:
    }
  }
}
