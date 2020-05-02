package com.github.magx2.steroids.collections.maps;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * A map that cannot be modified after creation.
 *
 * @param <KeyT>   Key type
 * @param <ValueT> Value type
 */
public interface ImmutableMap<KeyT, ValueT> extends Map<KeyT, ValueT> {
    /**
     * Coverts to Map that is mutable.
     * <p>
     * Original map still cannot be changed.
     *
     * @return new map that is mutable.
     */
    @NotNull
    Map<KeyT, ValueT> toMutableMap();

    /**
     * Create new {@link ImmutableMap} from this one that will contains given entry.
     *
     * @param key   entry key
     * @param value entry value
     * @return new {@link ImmutableMap} that has given entry
     */
    @NotNull
    ImmutableMap<KeyT, ValueT> putToNew(final KeyT key, final ValueT value);

    /**
     * Create new {@link ImmutableMap} from this one that will contains all entries from given map.
     *
     * @param map a map to add to new {@link ImmutableMap}
     * @return new {@link ImmutableMap} that has given entry
     */
    @NotNull
    ImmutableMap<KeyT, ValueT> putAllToNew(@NotNull Map<KeyT, ValueT> map);

    /**
     * Create new {@link ImmutableMap} from this one that will not contains given key.
     *
     * @param key key to remove from new map
     * @return new {@link ImmutableMap} that has not entry with given key
     */
    @NotNull
    ImmutableMap<KeyT, ValueT> removeFromNew(final KeyT key);

    /**
     * This method is unsupported.
     *
     * @throws UnsupportedOperationException always
     * @see ImmutableMap::putToNew
     */
    @Override
    default ValueT put(final KeyT key, final ValueT value) {
        throw new UnsupportedOperationException("This is immutable map! Puts are not supported!");
    }

    /**
     * This method is unsupported.
     *
     * @throws UnsupportedOperationException always
     * @see ImmutableMap::removeFromNew
     */
    @Override
    default ValueT remove(final Object key) {
        throw new UnsupportedOperationException("This is immutable map! Removes are not supported!");
    }

    /**
     * This method is unsupported.
     *
     * @throws UnsupportedOperationException always
     * @see ImmutableMap::putAllToNew
     */
    @Override
    default void putAll(final Map<? extends KeyT, ? extends ValueT> m) {
        throw new UnsupportedOperationException("This is immutable map! Put all are not supported!");
    }

    /**
     * This method is unsupported.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    default void clear() {
        throw new UnsupportedOperationException("This is immutable map! Clear is not supported!");
    }

}
