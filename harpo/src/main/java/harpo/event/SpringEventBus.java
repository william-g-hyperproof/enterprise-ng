package harpo.event;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringEventBus<T extends Serializable> implements EventBus<T> {
  @Autowired
  private Set<EventListener<T>> listeners;

  @Override
  public boolean dispatchEvent(T event) throws Throwable {
    // Here, "success" means that all listeners received the event
    boolean success = true;

    for (var listener : listeners) {
      try {
        success = success && listener.receiveEvent(event);
      } catch (Throwable e) {
        // TODO: write to a logging facility
      }
    }

    return success;
  }
}
