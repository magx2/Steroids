package com.github.steroids.collections.maps;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A Map Entry that cannot be changed.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public final class ImmutableMapEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private final V value;

    public ImmutableMapEntry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    /**
     * This operation is unsuported. Always throws {@link UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public V setValue(final V value) {
        throw new UnsupportedOperationException("This is immutable MapEntry!");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ImmutableMapEntry<?, ?> that = (ImmutableMapEntry<?, ?>) o;

        if (!Objects.equals(key, that.key)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ImmutableMapEntry.class.getSimpleName() + "[", "]")
                       .add("key=" + key)
                       .add("value=" + value)
                       .toString();
    }
}
