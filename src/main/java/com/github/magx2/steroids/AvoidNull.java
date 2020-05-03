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

public final class AvoidNull {
    /**
     * This is an utils class. Never instantiate it.
     */
    private AvoidNull() {
    }

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

    @NotNull
    public static <T> Collection<T> orDefault(Collection<T> collection) {
        if (collection != null) {
            return collection;
        } else {
            return new ArrayList<>(0);
        }
    }

    @NotNull
    public static <T> List<T> orDefault(List<T> list) {
        if (list != null) {
            return list;
        } else {
            return new ArrayList<>(0);
        }
    }

    @NotNull
    public static <T> Set<T> orDefault(Set<T> set) {
        if (set != null) {
            return set;
        } else {
            return new HashSet<>();
        }
    }

    @NotNull
    public static <T extends Comparable<T>> SortedSet<T> orDefault(SortedSet<T> sortedSet) {
        if (sortedSet != null) {
            return sortedSet;
        } else {
            return new TreeSet<>();
        }
    }

    @NotNull
    public static <K, V> Map<K, V> orDefault(Map<K, V> map) {
        if (map != null) {
            return map;
        } else {
            return new HashMap<>();
        }
    }

    @NotNull
    public static <K extends Comparable<K>, V> SortedMap<K, V> orDefault(SortedMap<K, V> sortedMap) {
        if (sortedMap != null) {
            return sortedMap;
        } else {
            return new TreeMap<>();
        }
    }

    @NotNull
    public static <T> Stream<T> orDefault(Stream<T> stream) {
        if (stream != null) {
            return stream;
        } else {
            return Stream.empty();
        }
    }
}
