package game;

import javax.swing.JFrame;


public class FrameSetter {

  private JFrame jframe;

  public FrameSetter(int width, int height, MainGame app) {
    this.jframe = new JFrame("Battle Game");

    jframe.setSize(width, height);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(app);
    jframe.setVisible(true);

    app.start();
  }
}
