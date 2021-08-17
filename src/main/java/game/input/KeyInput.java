package game.input;

public class KeyInput extends Input {

  public enum KeyType {
    ACCELERATE,
    DECELERATE,
    RIGHT_ROTATE,
    LEFT_ROTATE,
    FIRE
  }

  private int keyCode;

  private KeyType keyType;

  private boolean heldDown;

  public KeyInput(int keyCode, boolean heldDown, KeyType keyType) {
    this.keyCode = keyCode;
    this.heldDown = heldDown;
    this.keyType = keyType;
  }

  public KeyType getKeyType() {
    return keyType;
  }

  public void setKeyType(KeyType keyType) {
    this.keyType = keyType;
  }

  public int getKeyCode() {
    return keyCode;
  }

  public void setKeyCode(int keyCode) {
    this.keyCode = keyCode;
  }

  public boolean isHeldDown() {
    return heldDown;
  }

  public void setHeldDown(boolean heldDown) {
    this.heldDown = heldDown;
  }
}
