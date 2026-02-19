package harpo.event;

import org.springframework.stereotype.Component;

@Component
public class DemoEventListener implements EventListener<DemoEvent> {

  @Override
  public boolean receiveEvent(DemoEvent event) throws Throwable {
    System.out.println("Received Event: " + event.message());
    return true;
  }
}
