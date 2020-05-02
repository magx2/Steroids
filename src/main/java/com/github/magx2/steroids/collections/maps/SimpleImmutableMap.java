package com.github.magx2.steroids.collections.maps;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

/**
 * Simple implementation of {@link ImmutableMap} that is backed by {@link Map}.
 *
 * @param <KeyT>   Key type
 * @param <ValueT> Value type
 */
public final class SimpleImmutableMap<KeyT, ValueT> implements ImmutableMap<KeyT, ValueT> {
    private final Map<KeyT, ValueT> internalMap;
    private Set<Entry<KeyT, ValueT>> entrySet;

    @NotNull
    public static <K, V> SimpleImmutableMap<K, V> from(@NotNull Map<K, V> map) {
        return new SimpleImmutableMap<>(unmodifiableMap(new HashMap<>(map)));
    }

    @NotNull
    public static <K, V> SimpleImmutableMap<K, V> from(@NotNull Class<K> keyClass,
                                                       @NotNull Class<V> valueClass,
                                                       @NotNull Object... keyValues) {
        requireNonNull(keyClass, "keyClass cannot be null!");
        requireNonNull(valueClass, "valueClass cannot be null!");
        requireNonNull(keyValues, "keyValues cannot be null!");
        if (keyValues.length % 2 != 0) {
            throw new IllegalArgumentException(
                    "Number of items in `keyValues` needs to be even! " +
                            "Got " + keyValues.length + " elements in list.");
        }
        Map<K, V> newMap = new HashMap<>();
        for (int i = 0; i < keyValues.length - 1; i += 2) {
            final Object key = keyValues[i];
            final Object value = keyValues[i + 1];
            if (!keyClass.isInstance(key)) {
                throw new IllegalArgumentException(
                        "Key at index " + i + " is not type of `" + keyClass.getCanonicalName() + "`! Key=" + key);
            }
            if (!valueClass.isInstance(value)) {
                throw new IllegalArgumentException(
                        "Value at index " + (i + 1) + " is not type of `" + valueClass.getCanonicalName() + "`! " +
                                "Value=" + value);
            }
            newMap.put(keyClass.cast(key), valueClass.cast(value));
        }
        return new SimpleImmutableMap<>(unmodifiableMap(newMap));
    }

    private SimpleImmutableMap(@NotNull final Map<KeyT, ValueT> internalMap) {
        this.internalMap = requireNonNull(internalMap);
        this.entrySet = internalMap.entrySet()
                                .stream()
                                .map(ImmutableMapEntry::fromMapEntry)
                                .collect(Collectors.toSet());
    }

    @Override
    public Map<KeyT, ValueT> toMutableMap() {
        return new HashMap<>(internalMap);
    }

    @NotNull
    @Override
    public ImmutableMap<KeyT, ValueT> putToNew(final KeyT key, final ValueT value) {
        final HashMap<KeyT, ValueT> newMap = new HashMap<>(internalMap);
        newMap.put(key, value);
        return from(newMap);
    }

    @NotNull
    @Override
    public ImmutableMap<KeyT, ValueT> putAllToNew(@NotNull final Map<KeyT, ValueT> map) {
        requireNonNull(map, "Given map cannot be null!");
        final HashMap<KeyT, ValueT> newMap = new HashMap<>(internalMap);
        newMap.putAll(map);
        return from(newMap);
    }

    @NotNull
    @Override
    public ImmutableMap<KeyT, ValueT> removeFromNew(final KeyT key) {
        final HashMap<KeyT, ValueT> newMap = new HashMap<>(internalMap);
        newMap.remove(key);
        return from(newMap);
    }

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return internalMap.containsValue(value);
    }

    @Override
    public ValueT get(final Object key) {
        return internalMap.get(key);
    }

    @Override
    public Set<KeyT> keySet() {
        return unmodifiableSet(internalMap.keySet());
    }

    @Override
    public Collection<ValueT> values() {
        return unmodifiableCollection(internalMap.values());
    }

    @Override
    public Set<Entry<KeyT, ValueT>> entrySet() {
        return entrySet;
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(", ", SimpleImmutableMap.class.getSimpleName() + "[", "]");
        for (Map.Entry<KeyT, ValueT> entry : entrySet) {
            joiner.add(entry.getKey() + "=" + entry.getValue());
        }
        return joiner.toString();
    }
}
