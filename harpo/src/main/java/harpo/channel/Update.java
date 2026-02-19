package harpo.channel;

import java.io.Serializable;

/**
 * A channel update is an event to be delivered to interested listeners.
 * The update itself holds metadata (e.g. a path),
 * and wraps a more specialized object relevant to the specific event
 * that was being broadcast, e.g. an Update
 */
public interface Update<T extends Serializable> {
  /**
   * Returns the channel for this update, e.g. /foo/bar.
   * Subscribers who express interest in this channel will receive this update.
   */
  public String getChannel();

  public T getUpdateContents();
}