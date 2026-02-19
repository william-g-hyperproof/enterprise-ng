package harpo.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harpo.channel.Broadcaster;
import harpo.channel.Update;
import harpo.event.DemoEvent;
import harpo.event.EventBus;

@CrossOrigin
@RestController
public class HomeEndpoint {
  private EventBus<DemoEvent> demoEventBus;

  @Autowired
  private Broadcaster broadcaster;

  @Autowired
  public void setDemoEventBus(EventBus<DemoEvent> demoEventBus) {
    this.demoEventBus = demoEventBus;
  }

	@RequestMapping("/")
	String home() throws Throwable {
		var event = new DemoEvent("Hello World!");
    demoEventBus.dispatchEvent(event);

    Update<String> stringUpdate = new Update<String>() {
      @Override
      public String getChannel() {
        return "/foo";
      }

      @Override
      public String getUpdateContents() {
        return "Update String";
      }
    };
    broadcaster.broadcast(stringUpdate);

		return "Hello World!";
	}
}
