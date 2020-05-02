package com.github.magx2.steroids;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("ResultOfMethodCallIgnored")
class AvoidNullTest {
    @Test
    @DisplayName("should return first element from objects")
    void firstNonNullOneElements() {
        // when
        final String firstNonNull = AvoidNull.firstNonNull("x");

        // then
        assertThat(firstNonNull).isEqualTo("x");
    }

    @Test
    @DisplayName("should return second element from objects")
    void firstNonNullMultipleElements() {
        // when
        final String firstNonNull = AvoidNull.firstNonNull(null, "x", null, "y");

        // then
        assertThat(firstNonNull).isEqualTo("x");
    }

    @Test
    @DisplayName("should throw `NullPointerException` if only passed null")
    void firstNonNullOneNull() {
        // when
        ThrowableAssert.ThrowingCallable when = () -> AvoidNull.firstNonNull(null);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("should throw `NullPointerException` if all objects was null")
    void firstNonNullMultipleNulls() {
        // when
        ThrowableAssert.ThrowingCallable when = () -> AvoidNull.firstNonNull(null, null, null);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("should throw `NullPointerException` if all objects was null")
    void firstNonNullRestIsNull() {
        // when
        ThrowableAssert.ThrowingCallable when = () -> AvoidNull.firstNonNull(null, (Object[]) null);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }
}
