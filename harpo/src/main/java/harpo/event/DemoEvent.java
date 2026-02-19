package harpo.event;

import java.io.Serializable;

public record DemoEvent (String message) implements Serializable {}
