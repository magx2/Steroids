package com.github.magx2.steroids.collections.maps;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImmutableMapEntryTest {
    @Test
    @DisplayName("should create MapEntry with key and value")
    void simple() {
        // given
        final String key = "k";
        final String value = "v";

        // when
        final ImmutableMapEntry<String, String> entry = new ImmutableMapEntry<>(key, value);

        // then
        assertThat(entry.getKey()).isEqualTo(key);
        assertThat(entry.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should create MapEntry with null value")
    void nullValue() {
        // given
        final String key = "k";
        final String value = null;

        // when
        final ImmutableMapEntry<String, String> entry = new ImmutableMapEntry<>(key, value);

        // then
        assertThat(entry.getKey()).isEqualTo(key);
        assertThat(entry.getValue()).isNull();
    }

    @Test
    @DisplayName("should create MapEntry with null key")
    void nullKey() {
        // given
        final String key = null;
        final String value = "v";

        // when
        final ImmutableMapEntry<String, String> entry = new ImmutableMapEntry<>(key, value);

        // then
        assertThat(entry.getKey()).isNull();
        assertThat(entry.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should throw exception when trying to set value")
    void setValue() {
        // given
        final ImmutableMapEntry<String, String> entry = new ImmutableMapEntry<>("k", "v");

        // when
        final ThrowableAssert.ThrowingCallable when = () -> entry.setValue("new v");

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
