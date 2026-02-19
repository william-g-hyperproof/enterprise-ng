package harpo.channel;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class DemoSubscriber implements ChannelSubscriber {
  @Override
  public Collection<String> getChannels() {
    return Set.of("/foo", "/bar");
  }

  @SuppressWarnings("rawtypes")
  @Override
  public void receiveUpdate(Update update) {
    System.out.println("Received update on channel: " + update.getChannel());
    var cargo = update.getUpdateContents();
    if (cargo instanceof String) {
      System.out.println("Received string: " + cargo);
    }
  }
}
