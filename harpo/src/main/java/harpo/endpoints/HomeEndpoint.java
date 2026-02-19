package harpo.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harpo.event.DemoEvent;
import harpo.event.EventBus;

@CrossOrigin
@RestController
public class HomeEndpoint {
  private EventBus<DemoEvent> demoEventBus;

  @Autowired
  public void setDemoEventBus(EventBus<DemoEvent> demoEventBus) {
    this.demoEventBus = demoEventBus;
  }

	@RequestMapping("/")
	String home() throws Throwable {
		var event = new DemoEvent("Hello World!");
    demoEventBus.dispatchEvent(event);

		return "Hello World!";
	}
}
