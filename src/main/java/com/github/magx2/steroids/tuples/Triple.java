package com.github.magx2.steroids.tuples;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

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

    public Optional<FirstT> toOptional() {
        return Optional.ofNullable(first);
    }

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

    public Triple<FirstT, SecondT, ThirdT> setFirst(final FirstT first) {
        return new Triple<>(first, second, third);
    }

    public Triple<FirstT, SecondT, ThirdT> setSecond(final SecondT second) {
        return new Triple<>(first, second, third);
    }

    public Triple<FirstT, SecondT, ThirdT> setThird(final ThirdT third) {
        return new Triple<>(first, second, third);
    }

    public <FirstT2, SecondT2, ThirdT2> Triple<FirstT2, SecondT2, ThirdT2> map(
            Function<FirstT, FirstT2> firstMapper,
            Function<SecondT, SecondT2> secondMapper,
            Function<ThirdT, ThirdT2> thirdMapper) {
        return new Triple<>(
                firstMapper.apply(first),
                secondMapper.apply(second),
                thirdMapper.apply(third));
    }

    public <FirstT2> Triple<FirstT2, SecondT, ThirdT> mapFirst(Function<FirstT, FirstT2> firstMapper) {
        return new Triple<>(firstMapper.apply(first), second, third);
    }

    public <SecondT2> Triple<FirstT, SecondT2, ThirdT> mapSecond(Function<SecondT, SecondT2> secondMapper) {
        return new Triple<>(first, secondMapper.apply(second), third);
    }

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
