package events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBus {

  private static Map<EventType, Set<EventHandler>> eventBusList = new HashMap<>();

  /**
   * This method is used to subscribe to specific EventType with the given EventHandler.
   * @param eventType Enum used to describe the type of event.
   * @param eventHandler Concrete implementation of eventHandler.
   */
  public static void subscribe(EventType eventType, EventHandler eventHandler) {
    Set<EventHandler> eventHandlers = getEventHandlers(eventType);
    eventHandlers.add(eventHandler);
    eventBusList.put(eventType, eventHandlers);
  }

  /**
   * Use this method to publish the event to subscribed channels.
   * @param event
   */
  public static void publish(GameEvent event) {
    EventType eventType = event.getEventType();

    Set<EventHandler> eventHandlers = getEventHandlers(eventType);

    eventHandlers.forEach(eventHandler -> eventHandler.onEvent(event));
  }

  /**
   * Use this method to unsubscribe from dead events or eventHandlers.
   * @param eventType
   * @param eventHandler
   */
  public static void unsubscribe(EventType eventType, EventHandler eventHandler) {
    Set<EventHandler> eventHandlers = getEventHandlers(eventType);
    eventHandlers.remove(eventHandler);
  }

  private static Set<EventHandler> getEventHandlers(EventType eventType) {
    Set<EventHandler> eventHandlers = eventBusList.get(eventType);
    return eventHandlers == null ? new HashSet<>() : eventHandlers;
  }
}
