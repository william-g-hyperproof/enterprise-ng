package harpo.channel;

import java.util.Collection;

public interface ChannelSubscriber {
  /**
   * Return a list of paths that this subscriber is interested in.
   * Currently, paths can only be fixed strings.
   * In the future, we'd also accept globs, regex, or whatever.
   */
  public Collection<String> getChannels();

  @SuppressWarnings("rawtypes")
  public void receiveUpdate(Update update);
}