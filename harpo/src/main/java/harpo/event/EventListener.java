package harpo.event;

import java.io.Serializable;

/**
 * Implement this interface to receive events of a particular type.
 * Event buses will use Spring inversion of control to detect listeners
 * at runtime, so there's no need for registration.
 */
public interface EventListener<T extends Serializable> {
  /**
   * This method is called when an event is received.
   * 
   * @param event the event to be processed.
   * @return true if the event listener can be said to have processed the event
   */
  public boolean receiveEvent(T event) throws Throwable;
}
