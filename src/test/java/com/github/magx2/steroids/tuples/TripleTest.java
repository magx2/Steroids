package com.github.magx2.steroids.tuples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.magx2.steroids.tuples.Triple.triple;
import static org.assertj.core.api.Assertions.assertThat;

class TripleTest {
    @Test
    @DisplayName("should create triple")
    void createPair() {
        // when
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // then
        assertThat(triple.getFirst()).isEqualTo("x");
        assertThat(triple.getSecond()).isEqualTo(1);
        assertThat(triple.getThird()).isTrue();
    }

    @Test
    @DisplayName("should map Triple to Optional")
    void optional() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Optional<String> optional = triple.toOptional();

        // then
        assertThat(optional).contains("x");
    }

    @Test
    @DisplayName("should map Pair to empty Optional")
    void emptyOptional() {
        // given
        final Triple<String, Integer, Boolean> triple = triple(null, 1, true);

        // when
        final Optional<String> optional = triple.toOptional();

        // then
        assertThat(optional).isEmpty();
    }

    @Test
    @DisplayName("should map Triple to Pair")
    void pair() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Pair<String, Integer> pair = triple.toPair();

        // then
        assertThat(pair.getFirst()).isEqualTo("x");
        assertThat(pair.getSecond()).isEqualTo(1);
    }

    @Test
    @DisplayName("should set first")
    void setFirst() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.setFirst("y");

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("y");
        assertThat(secondTriple.getSecond()).isEqualTo(1);
        assertThat(secondTriple.getThird()).isTrue();
    }

    @Test
    @DisplayName("should set second")
    void setSecond() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.setSecond(2);

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("x");
        assertThat(secondTriple.getSecond()).isEqualTo(2);
        assertThat(secondTriple.getThird()).isTrue();
    }

    @Test
    @DisplayName("should set third")
    void setThird() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.setThird(false);

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("x");
        assertThat(secondTriple.getSecond()).isEqualTo(1);
        assertThat(secondTriple.getThird()).isFalse();
    }

    @Test
    @DisplayName("should map Triple to another Triple")
    void map() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.map(
                f -> f + "yz",
                s -> s + 1,
                b -> !b);

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("xyz");
        assertThat(secondTriple.getSecond()).isEqualTo(2);
        assertThat(secondTriple.getThird()).isFalse();
    }

    @Test
    @DisplayName("should map first")
    void mapFirst() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.mapFirst(f -> f + "yz");

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("xyz");
        assertThat(secondTriple.getSecond()).isEqualTo(1);
        assertThat(secondTriple.getThird()).isTrue();
    }

    @Test
    @DisplayName("should map second")
    void mapSecond() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.mapSecond(f -> f + 1);

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("x");
        assertThat(secondTriple.getSecond()).isEqualTo(2);
        assertThat(secondTriple.getThird()).isTrue();
    }

    @Test
    @DisplayName("should map third")
    void mapThird() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final Triple<String, Integer, Boolean> secondTriple = triple.mapThird(f -> !f);

        // then
        assertThat(secondTriple.getFirst()).isEqualTo("x");
        assertThat(secondTriple.getSecond()).isEqualTo(1);
        assertThat(secondTriple.getThird()).isFalse();
    }

    @Test
    @DisplayName("two Triple with same first and second should be identical")
    void equals() {
        // given
        final Triple<String, Integer, Boolean> triple1 = triple("x", 1, true);
        final Triple<String, Integer, Boolean> triple2 = triple("x", 1, true);

        // when
        final boolean equals1 = triple1.equals(triple2);
        final boolean equals2 = triple2.equals(triple1);

        // then
        assertThat(equals1).isTrue();
        assertThat(equals2).isTrue();
        assertThat(triple1.hashCode()).isEqualTo(triple2.hashCode());
    }

    @Test
    @DisplayName("two Triples with different first should be different")
    void notEqualsFirst() {
        // given
        final Triple<String, Integer, Boolean> triple1 = triple("x", 1, true);
        final Triple<String, Integer, Boolean> triple2 = triple("y", 1, true);

        // when
        final boolean equals1 = triple1.equals(triple2);
        final boolean equals2 = triple2.equals(triple1);

        // then
        assertThat(equals1).isFalse();
        assertThat(equals2).isFalse();
    }

    @Test
    @DisplayName("two Triples with different second should be different")
    void notEqualsSecond() {
        // given
        final Triple<String, Integer, Boolean> triple1 = triple("x", 1, true);
        final Triple<String, Integer, Boolean> triple2 = triple("x", 2, true);

        // when
        final boolean equals1 = triple1.equals(triple2);
        final boolean equals2 = triple2.equals(triple1);

        // then
        assertThat(equals1).isFalse();
        assertThat(equals2).isFalse();
    }

    @Test
    @DisplayName("two Triples with different third should be different")
    void notEqualsThird() {
        // given
        final Triple<String, Integer, Boolean> triple1 = triple("x", 1, true);
        final Triple<String, Integer, Boolean> triple2 = triple("x", 1, false);

        // when
        final boolean equals1 = triple1.equals(triple2);
        final boolean equals2 = triple2.equals(triple1);

        // then
        assertThat(equals1).isFalse();
        assertThat(equals2).isFalse();
    }

    @Test
    @DisplayName("should have proper `toString`")
    void toStringTest() {
        // given
        final Triple<String, Integer, Boolean> triple = triple("x", 1, true);

        // when
        final String string = triple.toString();

        // then
        assertThat(string).isEqualTo("Triple[x, 1, true]");
    }
}
