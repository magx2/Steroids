package com.github.magx2.steroids.tuples;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

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

    public Optional<FirstT> toOptional() {
        return Optional.ofNullable(first);
    }

    public <ThirdT> Triple<FirstT, SecondT, ThirdT> toTriple(ThirdT third) {
        return new Triple<>(first, second, third);
    }

    public FirstT getFirst() {
        return first;
    }

    public SecondT getSecond() {
        return second;
    }

    public Pair<FirstT, SecondT> setFirst(final FirstT first) {
        return new Pair<>(first, second);
    }

    public Pair<FirstT, SecondT> setSecond(final SecondT second) {
        return new Pair<>(first, second);
    }

    public <FirstT2, SecondT2> Pair<FirstT2, SecondT2> map(
            Function<FirstT, FirstT2> firstMapper,
            Function<SecondT, SecondT2> secondMapper) {
        return new Pair<>(firstMapper.apply(first), secondMapper.apply(second));
    }

    public <FirstT2> Pair<FirstT2, SecondT> mapFirst(Function<FirstT, FirstT2> firstMapper) {
        return new Pair<>(firstMapper.apply(first), second);
    }

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
