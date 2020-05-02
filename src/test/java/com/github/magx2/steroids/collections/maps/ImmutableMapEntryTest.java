package com.github.magx2.steroids.collections.maps;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    @DisplayName("should create MapEntry with key and value (static method)")
    void simpleStaticMethod() {
        // given
        final String key = "k";
        final String value = "v";

        // when
        final ImmutableMapEntry<String, String> entry = ImmutableMapEntry.of(key, value);

        // then
        assertThat(entry.getKey()).isEqualTo(key);
        assertThat(entry.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should create MapEntry from another entry")
    void newFromEntry() {
        // given
        final String key = "k";
        final String value = "v";

        // when
        final ImmutableMapEntry<String, String> entry = ImmutableMapEntry.of(key, value);

        // then
        assertThat(entry.getKey()).isEqualTo(key);
        assertThat(entry.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("should create MapEntry with null key")
    void nullKey() {
        // given
        final String key = "k";
        final String value = "v";
        final HashMap<String, String> map = new HashMap<>();
        map.put(key, value);
        final Map.Entry<String, String> someEntry = map.entrySet().iterator().next();

        // when
        final ImmutableMapEntry<String, String> entry = ImmutableMapEntry.fromMapEntry(someEntry);

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
