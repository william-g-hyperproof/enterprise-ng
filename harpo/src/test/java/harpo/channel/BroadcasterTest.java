package harpo.channel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public class BroadcasterTest {
  @Test
  void verifyBroadcasterNotifiesSubscribers() {
    // Arrange
    final String channel = "/unit-test";

    var subscriber = new ChannelSubscriber() {
      boolean subscriberCalled = false;

      public boolean wasSubscriberCalled() {
        return subscriberCalled;
      }

      @Override
      public Collection<String> getChannels() {
        return Set.of(channel);
      }

      @Override
      public void receiveUpdate(@SuppressWarnings("rawtypes") Update update) {
        subscriberCalled = true;        
      }
    };

    // Act
    var broadcaster = new Broadcaster();
    broadcaster.setSubscribers(List.of(subscriber));
    broadcaster.broadcast(new Update<String>() {
      @Override
      public String getChannel() {
        return channel;
      }

      @Override
      public String getUpdateContents() {
        return "Hail Mary";
      }      
    });

    // Assert
    assertTrue(subscriber.wasSubscriberCalled(), "Subscriber wasn't called");
  }

  @Test
  void verifyBroadcasterSkipsUninterestedSubscribers() {
    // Arrange
    var subscriber = new ChannelSubscriber() {
      boolean subscriberCalled = false;

      public boolean wasSubscriberCalled() {
        return subscriberCalled;
      }

      @Override
      public Collection<String> getChannels() {
        return Set.of("/subscriber-channel"); // Shouldn't match the channel of the update
      }

      @Override
      public void receiveUpdate(@SuppressWarnings("rawtypes") Update update) {
        subscriberCalled = true;        
      }
    };

    // Act
    var broadcaster = new Broadcaster();
    broadcaster.setSubscribers(List.of(subscriber));
    broadcaster.broadcast(new Update<String>() {
      @Override
      public String getChannel() {
        return "/update-channel"; // Shouldn't match the channel of the subscriber
      }

      @Override
      public String getUpdateContents() {
        return "Hail Mary";
      }      
    });

    // Assert
    assertFalse(subscriber.wasSubscriberCalled(), "Subscriber was called");
  }
}
