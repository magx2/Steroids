# Immutable MapEntry

An implementation of `MapEntry` that is immutable.

Example:

```java
final ImmutableMapEntry<String, String> entry = new ImmutableMapEntry<>("k", "v");
Syste.out.println("Key=" + entry.getKey()); // prints Key=k
Syste.out.println("Value=" + entry.getValue); // prints Value=v
entry.setValue("new value"); // throws UnsupportedOperationException
```
