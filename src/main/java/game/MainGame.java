package game;

import game.context.GameContext;
import game.context.MenuContext;
import game.input.listener.KeyListener;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class MainGame extends Canvas implements Runnable{

  private Thread thread;

  static int totalSeconds = 1;

  private boolean running = false;

  private static GameState state = GameState.GAME;

  private GameContext gameContext = new GameContext();

  private MenuContext menuContext = new MenuContext();

  public MainGame() {
    this.addKeyListener(new KeyListener());
  }

  @Override
  public void run() {
    double target = 60.0;
    double nsPerTick = 1000000000.0 / target;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double unprocessed = 0.0;
    int fps = 0;
    int tps = 0;
    boolean canRender = false;

    while (running) {
      long now = System.nanoTime();
      long difference = now - lastTime;
      unprocessed += difference / nsPerTick;
      lastTime = now;

      if (unprocessed >= 1.0) {
        if(state == GameState.GAME) {
          gameContext.update(difference);
        } else {
          menuContext.update(difference);
        }
        unprocessed--;
        tps++;
        canRender = true;
      } else {
        canRender = false;
      }

      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (canRender) {
        render();
        fps++;
      }

      if (System.currentTimeMillis() - 1000 > timer) {
        timer += 1000;
        System.out.printf("FPS: %d | TPS: %d | total seconds : %d\n", fps, tps, totalSeconds++);
        fps = 0;
        tps = 0;
      }

    }
  }

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics2D g = (Graphics2D) bs.getDrawGraphics();

    clearScreen(g);

    if(state == GameState.GAME) {
      gameContext.render(g);
    } else {
      menuContext.render(g);
    }


    g.dispose();
    bs.show();
  }

  public void clearScreen(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 800,800);
  }

  public void start() {
    thread = new Thread(this);
    running = true;
    setBackground(Color.BLACK);
    thread.start();
  }

  public void stop() {
    try {
      running = false;
      thread.join();
    } catch (Exception E) {
      E.printStackTrace();
    }
  }

  public static void main(String args[]) {
    new FrameSetter(800, 800, new MainGame());
  }

  public static GameState getState() {
    return state;
  }

  public static void setState(GameState state) {
    MainGame.state = state;
  }
}
