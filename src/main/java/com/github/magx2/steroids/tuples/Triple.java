package com.github.magx2.steroids.tuples;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Immutable triple of three values.
 *
 * @param <FirstT>  Type of the first parameter
 * @param <SecondT> Type of the second parameter
 * @param <ThirdT>  Type of the third parameter
 */
public final class Triple<FirstT, SecondT, ThirdT> {
    private final FirstT first;
    private final SecondT second;
    private final ThirdT third;

    public static <FirstT, SecondT, ThirdT> Triple<FirstT, SecondT, ThirdT> triple(
            final FirstT first,
            final SecondT second,
            final ThirdT third) {
        return new Triple<>(first, second, third);
    }

    public Triple(final FirstT first, final SecondT second, final ThirdT third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Takes <code>first</code> value from Triple and pack it into {@link Optional}.
     *
     * @return Optional of first
     */
    public Optional<FirstT> toOptional() {
        return Optional.ofNullable(first);
    }

    /**
     * Creates {@link Pair} from <code>first</code> and <code>second</code>.
     *
     * @return Pair made from this Triple
     */
    public Pair<FirstT, SecondT> toPair() {
        return new Pair<>(first, second);
    }

    public FirstT getFirst() {
        return first;
    }

    public SecondT getSecond() {
        return second;
    }

    public ThirdT getThird() {
        return third;
    }

    /**
     * Creates new Triple and sets new first.
     * <p>
     * Note: Triple class is immutable! This set creates new Triple.
     *
     * @param first new first to set
     * @return new Triple with set first ; NOT same Triple as this
     */
    public Triple<FirstT, SecondT, ThirdT> setFirst(final FirstT first) {
        return new Triple<>(first, second, third);
    }

    /**
     * Creates new Triple and sets new second.
     * <p>
     * Note: Triple class is immutable! This set creates new Triple.
     *
     * @param second new second to set
     * @return new Triple with set second ; NOT same Triple as this
     */
    public Triple<FirstT, SecondT, ThirdT> setSecond(final SecondT second) {
        return new Triple<>(first, second, third);
    }

    /**
     * Creates new Triple and sets new third.
     * <p>
     * Note: Triple class is immutable! This set creates new Triple.
     *
     * @param third new third to set
     * @return new Triple with set third ; NOT same Triple as this
     */
    public Triple<FirstT, SecondT, ThirdT> setThird(final ThirdT third) {
        return new Triple<>(first, second, third);
    }

    /**
     * Map this Triple into a new one.
     *
     * @param firstMapper  function to map first parameter
     * @param secondMapper function to map second parameter
     * @param thirdMapper  function to map third parameter
     * @param <FirstT2>    type of first in new Pair
     * @param <SecondT2>   type of second in new Pair
     * @param <ThirdT2>    type of third in new Pair
     * @return new Triple
     */
    public <FirstT2, SecondT2, ThirdT2> Triple<FirstT2, SecondT2, ThirdT2> map(
            Function<FirstT, FirstT2> firstMapper,
            Function<SecondT, SecondT2> secondMapper,
            Function<ThirdT, ThirdT2> thirdMapper) {
        return new Triple<>(
                firstMapper.apply(first),
                secondMapper.apply(second),
                thirdMapper.apply(third));
    }

    /**
     * Map this Triple into a new one.
     *
     * @param firstMapper function to map first parameter
     * @param <FirstT2>   type of first in new Triple
     * @return new Triple
     */
    public <FirstT2> Triple<FirstT2, SecondT, ThirdT> mapFirst(Function<FirstT, FirstT2> firstMapper) {
        return new Triple<>(firstMapper.apply(first), second, third);
    }

    /**
     * Map this Triple into a new one.
     *
     * @param secondMapper function to map second parameter
     * @param <SecondT2>   type of second in new Triple
     * @return new Triple
     */
    public <SecondT2> Triple<FirstT, SecondT2, ThirdT> mapSecond(Function<SecondT, SecondT2> secondMapper) {
        return new Triple<>(first, secondMapper.apply(second), third);
    }

    /**
     * Map this Triple into a new one.
     *
     * @param thirdMapper function to map third parameter
     * @param <ThirdT2>   type of third in new Triple
     * @return new Triple
     */
    public <ThirdT2> Triple<FirstT, SecondT, ThirdT2> mapThird(Function<ThirdT, ThirdT2> thirdMapper) {
        return new Triple<>(first, second, thirdMapper.apply(third));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;

        if (!Objects.equals(first, triple.first)) return false;
        if (!Objects.equals(second, triple.second)) return false;
        return Objects.equals(third, triple.third);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        result = 31 * result + (third != null ? third.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Triple[" +
                       first + ", " +
                       second + ", " +
                       third + "]";
    }
}
