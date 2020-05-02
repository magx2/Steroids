# Immutable Map (extends `java.util.Map`)

```java
ImmutableMap<String, Integer> immutableMap = SimpleImmutableMap.from(
        String.class, Integer.class,
        "k1", 1,
        "k2", 2,
        "k3", 3);
System.out.println("Has key `k1`? " + immutableMap.containsKey("k1")); // prints Has key `k1`? true 
System.out.println("Has key `k4`? " + immutableMap.containsKey("k4")); // prints Has key `k4`? false
```
`ImmutableMap` is not supporting methods that changes internal state of the map (like `put` and `remove`). Instead you can copy existing map and add/remove entry to it.  
```java
immutableMap.put("k4", 4); // throws UnsupportedOperationException
// Instead do this:
ImmutableMap<String, Integer> newImmutableMap = immutableMap.putToNew("k4", 4);
System.out.println("Has key `k4`? " + newImmutableMap.containsKey("k4")); // prints Has key `k4`? true
System.out.println("Has key `k4`? " + immutableMap.containsKey("k4")); // prints Has key `k4`? false
```
If you want to go back to old plain Javas `Map` you can invoke `toMutableMap` method:
```java
Map<String, Integer> oldPlainMap = immutableMap.toMutableMap();
oldPlainMap.put("k4", 4); // works
```
And here is interesting pattern of creating Javas `Map`:
```java
Map<String, Integer> oldPlainMap = SimpleImmutableMap.from(
        String.class, Integer.class,
        "k1", 1,
        "k2", 2,
        "k3", 3).toMutableMap();
oldPlainMap.put("k4", 4); // works
```

# Immutable MapEntry

An implementation of `MapEntry` that is immutable.

Example:

```java
final ImmutableMapEntry<String, String> entry = new ImmutableMapEntry<>("k", "v");
Syste.out.println("Key=" + entry.getKey()); // prints Key=k
Syste.out.println("Value=" + entry.getValue); // prints Value=v
entry.setValue("new value"); // throws UnsupportedOperationException
```
