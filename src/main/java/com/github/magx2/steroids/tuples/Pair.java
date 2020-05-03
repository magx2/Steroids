package com.github.magx2.steroids.tuples;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Immutable pair of two values.
 *
 * @param <FirstT>  Type of the first parameter
 * @param <SecondT> Type of the second parameter
 */
public final class Pair<FirstT, SecondT> implements Map.Entry<FirstT, SecondT> {
    private final FirstT first;
    private final SecondT second;

    public static <FirstT, SecondT> Pair<FirstT, SecondT> pair(final FirstT first, final SecondT second) {
        return new Pair<>(first, second);
    }

    public Pair(final FirstT first, final SecondT second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Takes <code>first</code> value from Pair and pack it into {@link Optional}.
     *
     * @return Optional of first
     */
    public Optional<FirstT> toOptional() {
        return Optional.ofNullable(first);
    }

    /**
     * Creates {@link Triple} from Pairs <code>first</code>, <code>second</code> and given <code>third</code>.
     *
     * @param third    third value passed to {@link Triple}
     * @param <ThirdT> type of the third parameter
     * @return Triple made from this Pair and given third
     */
    public <ThirdT> Triple<FirstT, SecondT, ThirdT> toTriple(ThirdT third) {
        return new Triple<>(first, second, third);
    }

    public FirstT getFirst() {
        return first;
    }

    public SecondT getSecond() {
        return second;
    }

    /**
     * Creates new Pair and sets new first.
     * <p>
     * Note: Pair class is immutable! This set creates new Pair.
     *
     * @param first new first to set
     * @return new Pair with set first ; NOT same Pair as this
     */
    public Pair<FirstT, SecondT> setFirst(final FirstT first) {
        return new Pair<>(first, second);
    }

    /**
     * Creates new Pair and sets new second.
     * <p>
     * Note: Pair class is immutable! This set creates new Pair.
     *
     * @param second new second to set
     * @return new Pair with set second ; NOT same Pair as this
     */
    public Pair<FirstT, SecondT> setSecond(final SecondT second) {
        return new Pair<>(first, second);
    }

    /**
     * Map this Pair into a new one.
     *
     * @param firstMapper  function to map first parameter
     * @param secondMapper function to map second parameter
     * @param <FirstT2>    type of first in new Pair
     * @param <SecondT2>   type of second in new Pair
     * @return new Pair
     */
    public <FirstT2, SecondT2> Pair<FirstT2, SecondT2> map(
            Function<FirstT, FirstT2> firstMapper,
            Function<SecondT, SecondT2> secondMapper) {
        return new Pair<>(firstMapper.apply(first), secondMapper.apply(second));
    }

    /**
     * Map this Pair into a new one.
     *
     * @param firstMapper function to map first parameter
     * @param <FirstT2>   type of first in new Pair
     * @return new Pair
     */
    public <FirstT2> Pair<FirstT2, SecondT> mapFirst(Function<FirstT, FirstT2> firstMapper) {
        return new Pair<>(firstMapper.apply(first), second);
    }

    /**
     * Map this Pair into a new one.
     *
     * @param secondMapper function to map second parameter
     * @param <SecondT2>   type of second in new Pair
     * @return new Pair
     */
    public <SecondT2> Pair<FirstT, SecondT2> mapSecond(Function<SecondT, SecondT2> secondMapper) {
        return new Pair<>(first, secondMapper.apply(second));
    }

    @Override
    public FirstT getKey() {
        return first;
    }

    @Override
    public SecondT getValue() {
        return second;
    }

    /**
     * This is unsupported. Pair is immutable.
     *
     * @return never
     * @throws UnsupportedOperationException always
     */
    @Override
    public SecondT setValue(final SecondT value) {
        throw new UnsupportedOperationException("This map entry is immutable!");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!Objects.equals(first, pair.first)) return false;
        return Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair[" + first + ", " + second + "]";
    }
}
