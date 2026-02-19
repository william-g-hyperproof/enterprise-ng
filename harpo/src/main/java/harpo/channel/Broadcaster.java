package harpo.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Broadcaster {
  private List<ChannelSubscriber> subscribers;

  @Autowired
  public void setSubscribers(List<ChannelSubscriber> subscribers) {
    this.subscribers = subscribers;
    // TODO: build a cached map of channel => list of subscribers
    // so we don't have to continually ask subscribers if they care
  }

  @SuppressWarnings("rawtypes")
  public void broadcast(Update update) {
    var channel = update.getChannel();
    var subscribersForChannel = findSubscribersForChannel(channel);
    for (var subscriber : subscribersForChannel) {
      subscriber.receiveUpdate(update);
    }
  }

  public List<ChannelSubscriber> findSubscribersForChannel(String channel) {
    // TODO: add pattern-matching or glob behavior to support wildcard subscriptions
    return subscribers.stream()
      .filter(subscriber -> subscriber.getChannels().contains(channel))
      .toList();
  }
}
