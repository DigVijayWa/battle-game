package game.input;

public class NetworkInput extends Input {

  private KeyInput keyInput;
  private MouseInput mouseInput;

  public NetworkInput(KeyInput keyInput, MouseInput mouseInput) {
    this.keyInput = keyInput;
    this.mouseInput = mouseInput;
  }

  public KeyInput getKeyInput() {
    return keyInput;
  }

  public void setKeyInput(KeyInput keyInput) {
    this.keyInput = keyInput;
  }

  public MouseInput getMouseInput() {
    return mouseInput;
  }

  public void setMouseInput(MouseInput mouseInput) {
    this.mouseInput = mouseInput;
  }
}
