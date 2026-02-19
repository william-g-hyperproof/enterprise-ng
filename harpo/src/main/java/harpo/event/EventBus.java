package harpo.event;

import java.io.Serializable;

/**
 * Implement this interface if you can dispatch events along a bus of some kind.
 */
public interface EventBus<T extends Serializable> {
  /**
   * Dispatches an arbitrary event to all interested listeners.
   * 
   * @param event the event to dispatch
   * @return true if the event was successfully dispatched
   * @throws an exception (e.g. IOException) depending on implementation
   */
  public boolean dispatchEvent(T event) throws Throwable;
}
