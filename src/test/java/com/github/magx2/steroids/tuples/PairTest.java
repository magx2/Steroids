package com.github.magx2.steroids.tuples;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.magx2.steroids.tuples.Pair.pair;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PairTest {
    @Test
    @DisplayName("should create pair")
    void createPair() {
        // when
        final Pair<String, Integer> pair = pair("x", 1);

        // then
        assertThat(pair.getFirst()).isEqualTo("x");
        assertThat(pair.getKey()).isEqualTo("x");
        assertThat(pair.getSecond()).isEqualTo(1);
        assertThat(pair.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("should map Pair to Optional")
    void optional() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Optional<String> optional = pair.toOptional();

        // then
        assertThat(optional).contains("x");
    }

    @Test
    @DisplayName("should map Pair to empty Optional")
    void emptyOptional() {
        // given
        final Pair<String, Integer> pair = pair(null, 1);

        // when
        final Optional<String> optional = pair.toOptional();

        // then
        assertThat(optional).isEmpty();
    }

    @Test
    @DisplayName("should map Pair to Triple")
    void triple() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Triple<String, Integer, Boolean> triple = pair.toTriple(true);

        // then
        assertThat(triple.getFirst()).isEqualTo("x");
        assertThat(triple.getSecond()).isEqualTo(1);
        assertThat(triple.getThird()).isTrue();
    }

    @Test
    @DisplayName("should set first")
    void setFirst() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Pair<String, Integer> secondPair = pair.setFirst("y");

        // then
        assertThat(secondPair.getFirst()).isEqualTo("y");
        assertThat(secondPair.getSecond()).isEqualTo(1);
    }

    @Test
    @DisplayName("should set second")
    void setSecond() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Pair<String, Integer> secondPair = pair.setSecond(2);

        // then
        assertThat(secondPair.getFirst()).isEqualTo("x");
        assertThat(secondPair.getSecond()).isEqualTo(2);
    }

    @Test
    @DisplayName("should map Pair to another Pair")
    void map() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Pair<String, Integer> secondPair = pair.map(
                f -> f + "yz",
                s -> s + 1);

        // then
        assertThat(secondPair.getFirst()).isEqualTo("xyz");
        assertThat(secondPair.getSecond()).isEqualTo(2);
    }

    @Test
    @DisplayName("should map first")
    void mapFirst() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Pair<String, Integer> secondPair = pair.mapFirst(f -> f + "yz");

        // then
        assertThat(secondPair.getFirst()).isEqualTo("xyz");
        assertThat(secondPair.getSecond()).isEqualTo(1);
    }

    @Test
    @DisplayName("should map second")
    void mapSecond() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final Pair<String, Integer> secondPair = pair.mapSecond(s -> s + 1);

        // then
        assertThat(secondPair.getFirst()).isEqualTo("x");
        assertThat(secondPair.getSecond()).isEqualTo(2);
    }

    @Test
    @DisplayName("should not allow to `setValue`")
    void setValue() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final ThrowableAssert.ThrowingCallable when = () -> pair.setValue(42);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("two Pairs with same first and second should be identical")
    void equals() {
        // given
        final Pair<String, Integer> pair1 = pair("x", 1);
        final Pair<String, Integer> pair2 = pair("x", 1);

        // when
        final boolean equals1 = pair1.equals(pair2);
        final boolean equals2 = pair2.equals(pair1);

        // then
        assertThat(equals1).isTrue();
        assertThat(equals2).isTrue();
        assertThat(pair1.hashCode()).isEqualTo(pair2.hashCode());
    }

    @Test
    @DisplayName("two Pairs with different first should be different")
    void notEqualsFirst() {
        // given
        final Pair<String, Integer> pair1 = pair("x", 1);
        final Pair<String, Integer> pair2 = pair("y", 1);

        // when
        final boolean equals1 = pair1.equals(pair2);
        final boolean equals2 = pair2.equals(pair1);

        // then
        assertThat(equals1).isFalse();
        assertThat(equals2).isFalse();
    }

    @Test
    @DisplayName("two Pairs with different second should be different")
    void notEqualsSecond() {
        // given
        final Pair<String, Integer> pair1 = pair("x", 1);
        final Pair<String, Integer> pair2 = pair("x", 2);

        // when
        final boolean equals1 = pair1.equals(pair2);
        final boolean equals2 = pair2.equals(pair1);

        // then
        assertThat(equals1).isFalse();
        assertThat(equals2).isFalse();
    }

    @Test
    @DisplayName("should have proper `toString`")
    void toStringTest() {
        // given
        final Pair<String, Integer> pair = pair("x", 1);

        // when
        final String string = pair.toString();

        // then
        assertThat(string).isEqualTo("Pair[x, 1]");
    }
}
