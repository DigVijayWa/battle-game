package game.input;

public class MouseInput extends Input {


  private int clickCount;

  private boolean heldDown;

  public MouseInput(int clickCount, boolean heldDown) {
    this.clickCount = clickCount;
    this.heldDown = heldDown;
  }

  public int getClickCount() {
    return clickCount;
  }

  public void setClickCount(int clickCount) {
    this.clickCount = clickCount;
  }

  public boolean isHeldDown() {
    return heldDown;
  }

  public void setHeldDown(boolean heldDown) {
    this.heldDown = heldDown;
  }
}
