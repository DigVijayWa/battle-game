package game.input.listener;

import events.EventBus;
import events.EventType;
import game.input.KeyInput;
import game.input.KeyInput.KeyType;
import game.input.event.KeyInputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class KeyListener implements java.awt.event.KeyListener {

  private static Map<Integer, Timer> pressedKeys = new HashMap<>();

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        if (!pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.put(e.getKeyCode(), new Timer());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
              new KeyInput(e.getKeyCode(), true, KeyType.ACCELERATE)));
        }
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        if (!pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.put(e.getKeyCode(), new Timer());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
              new KeyInput(e.getKeyCode(), true, KeyType.LEFT_ROTATE)));
        }
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        if (!pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.put(e.getKeyCode(), new Timer());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
              new KeyInput(e.getKeyCode(), true, KeyType.DECELERATE)));
        }
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        if (!pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.put(e.getKeyCode(), new Timer());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_PRESSED,
              new KeyInput(e.getKeyCode(), true, KeyType.RIGHT_ROTATE)));
        }
        break;
      default:
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        if (pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.remove(e.getKeyCode());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_RELEASED,
              new KeyInput(e.getKeyCode(), false, KeyType.ACCELERATE)));
        }
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        if (pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.remove(e.getKeyCode());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_RELEASED,
              new KeyInput(e.getKeyCode(), false, KeyType.LEFT_ROTATE)));
        }
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        if (pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.remove(e.getKeyCode());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_RELEASED,
              new KeyInput(e.getKeyCode(), false, KeyType.DECELERATE)));
        }
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        if (pressedKeys.containsKey(e.getKeyCode())) {
          pressedKeys.remove(e.getKeyCode());
          EventBus.publish(new KeyInputEvent(0, EventType.KEY_RELEASED,
              new KeyInput(e.getKeyCode(), false, KeyType.RIGHT_ROTATE)));
        }
        break;
      case KeyEvent.VK_SPACE:
        EventBus.publish(new KeyInputEvent(0, EventType.KEY_RELEASED,
            new KeyInput(e.getKeyCode(), false, KeyType.FIRE)));
        break;
      default:
    }
  }

  public static Map<Integer, Timer> getPressedKeys() {
    return pressedKeys;
  }
}
