# Avoid Null

`AvoidNull` is a utils class that helps you to avoid `NullPointerExceptions` in your code.

## First Non Null

`firstNonNull` and `tryFirstNonNull` are methods to help you pick first element that might be null or second, default one. 
```java
import static com.github.magx2.steroids.AvoidNull.*;

System.out.println(firstNonNull(null, "x", null, "y")); // prints "x" 
System.out.println(firstNonNull(null, null, null, null)); // throws NullPointerException 
System.out.println(firstNonNull((Object[]) null)); // throws NullPointerException

System.out.println(tryFirstNonNull(null, "x", null, "y")); // prints Optional[x] 
System.out.println(tryFirstNonNull(null, null, null, null)); // Optional.empty 
System.out.println(tryFirstNonNull((Object[]) null)); //  Optional.empty
```

## `orDefault`

`orDefault` methods allows you to change annoying `nulls` to empty collections.

```java
import static com.github.magx2.steroids.AvoidNull.*;

List<String> foo = asList("x", "y", "z");
System.out.println(orDefault(foo).size()); // prints 3

System.out.println(orDefault(null).size()); // prints 0
```