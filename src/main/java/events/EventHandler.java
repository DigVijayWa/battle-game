package events;

public interface EventHandler {

  /**
   * This event handler method is executed on the invocation of subscribed event. ALl the necessary
   * information is included in GameEvent.
   * @param event GameEvent object used by the event handler method.
   */
  void onEvent(GameEvent event);
}
