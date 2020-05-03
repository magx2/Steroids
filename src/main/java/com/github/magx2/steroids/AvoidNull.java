package com.github.magx2.steroids;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Utils class that helps you to avoid {@link NullPointerException}.
 */
public final class AvoidNull {
    /**
     * This is an utils class. Never instantiate it.
     */
    private AvoidNull() {
    }

    /**
     * Return first non null object.
     * <p>
     * Examples:
     * <p>
     * <code>firstNonNull(null, "x", null, "y"); // return `x`</code>
     * <p>
     * <code>firstNonNull(null, null, null, null); // throws NullPointerException</code>
     * <p>
     * <code>firstNonNull((Object[]) null); // throws NullPointerException</code>
     *
     * @param objects list of objects to take from
     * @param <T>     return type
     * @return first non null element from given objects
     * @throws NullPointerException if every item in objects was null or objects was null
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> T firstNonNull(T... objects) {
        if (objects != null) {
            for (T object : objects) {
                if (object != null) {
                    return object;
                }
            }
        }
        throw new NullPointerException("Everything was null!");
    }

    /**
     * Return {@link Optional} of first non null object or {@link Optional#empty()}.
     * <p>
     * Examples:
     * <p>
     * <code>tryFirstNonNull(null, "x", null, "y"); // return `Optional.of(x)`</code>
     * <p>
     * <code>tryFirstNonNull(null, null, null, null); // returns `Optional.empty()`</code>
     * <p>
     * <code>tryFirstNonNull((Object[]) null); // return `Optional.empty()`</code>
     *
     * @param objects list of objects to take from
     * @param <T>     return type
     * @return Optional of first non null element from given objects or Optional.empty() if everything was null
     */
    @SafeVarargs
    public static <T> Optional<T> tryFirstNonNull(T... objects) {
        if (objects != null) {
            for (T object : objects) {
                if (object != null) {
                    return Optional.of(object);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Return given collection or new empty collection if given was <code>null</code>.
     *
     * @param collection to test nullability
     * @param <T>        type of elements in collection
     * @return given collection or new empty collection if given was <code>null</code>
     */
    @NotNull
    public static <T> Collection<T> orDefault(Collection<T> collection) {
        if (collection != null) {
            return collection;
        } else {
            return new ArrayList<>(0);
        }
    }

    /**
     * Return given list or new empty list if given was <code>null</code>.
     *
     * @param list to test nullability
     * @param <T>  type of elements in list
     * @return given list or new empty list if given was <code>null</code>
     */
    @NotNull
    public static <T> List<T> orDefault(List<T> list) {
        if (list != null) {
            return list;
        } else {
            return new ArrayList<>(0);
        }
    }

    /**
     * Return given set or new empty set if given was <code>null</code>.
     *
     * @param set to test nullability
     * @param <T> type of elements in set
     * @return given set or new empty set if given was <code>null</code>
     */
    @NotNull
    public static <T> Set<T> orDefault(Set<T> set) {
        if (set != null) {
            return set;
        } else {
            return new HashSet<>();
        }
    }

    /**
     * Return given sorted set or new empty sorted set if given was <code>null</code>.
     *
     * @param sortedSet to test nullability
     * @param <T>       type of elements in sorted set
     * @return given sorted set or new empty sorted set if given was <code>null</code>
     */
    @NotNull
    public static <T extends Comparable<T>> SortedSet<T> orDefault(SortedSet<T> sortedSet) {
        if (sortedSet != null) {
            return sortedSet;
        } else {
            return new TreeSet<>();
        }
    }

    /**
     * Return given map or new empty map if given was <code>null</code>.
     *
     * @param map to test nullability
     * @param <K> type of keys in map
     * @param <V> type of values in map
     * @return given map or new empty map if given was <code>null</code>
     */
    @NotNull
    public static <K, V> Map<K, V> orDefault(Map<K, V> map) {
        if (map != null) {
            return map;
        } else {
            return new HashMap<>();
        }
    }

    /**
     * Return given sorted map or new empty sorted map if given was <code>null</code>.
     *
     * @param sortedMap to test nullability
     * @param <K>       type of keys in map
     * @param <V>       type of values in map
     * @return given sorted map or new empty sorted map if given was <code>null</code>
     */
    @NotNull
    public static <K extends Comparable<K>, V> SortedMap<K, V> orDefault(SortedMap<K, V> sortedMap) {
        if (sortedMap != null) {
            return sortedMap;
        } else {
            return new TreeMap<>();
        }
    }

    /**
     * Return given stream or new empty stream if given was <code>null</code>.
     *
     * @param stream to test nullability
     * @param <T>    type of elements in stream
     * @return given stream or new empty stream if given was <code>null</code>
     */
    @NotNull
    public static <T> Stream<T> orDefault(Stream<T> stream) {
        if (stream != null) {
            return stream;
        } else {
            return Stream.empty();
        }
    }
}
