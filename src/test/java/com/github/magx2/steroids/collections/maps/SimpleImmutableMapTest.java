package com.github.magx2.steroids.collections.maps;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleImmutableMapTest {
    @ParameterizedTest(name = "[{index}]should create SimpleImmutableMap from static method")
    @MethodSource
    void mapCreation(Supplier<SimpleImmutableMap<String, Integer>> supplier) {
        // when
        final SimpleImmutableMap<String, Integer> immutableMap = supplier.get();

        // then
        assertThat(immutableMap).hasSize(3);
        assertThat(immutableMap).isNotEmpty();
        assertThat(immutableMap).containsOnly(
                ImmutableMapEntry.of("k1", 1),
                ImmutableMapEntry.of("k2", 2),
                ImmutableMapEntry.of("k3", 3));
        assertThat(immutableMap.containsKey("k1")).isTrue();
        assertThat(immutableMap.containsKey("some key")).isFalse();
        assertThat(immutableMap.containsValue(1)).isTrue();
        assertThat(immutableMap.containsValue(99)).isFalse();
        assertThat(immutableMap.get("k1")).isEqualTo(1);
        assertThat(immutableMap.get("some key")).isNull();
        assertThat(immutableMap.keySet()).containsExactlyInAnyOrder("k1", "k2", "k3");
        assertThat(immutableMap.values()).containsExactlyInAnyOrder(1, 2, 3);
        assertThat(immutableMap.entrySet()).containsExactlyInAnyOrder(
                ImmutableMapEntry.of("k1", 1),
                ImmutableMapEntry.of("k2", 2),
                ImmutableMapEntry.of("k3", 3));
    }

    @SuppressWarnings("unused") // It's used by parametrized test
    static Stream<Supplier<SimpleImmutableMap<String, Integer>>> mapCreation() {
        return Stream.of(
                SimpleImmutableMapTest::fromMap,
                SimpleImmutableMapTest::fromList);
    }

    static SimpleImmutableMap<String, Integer> fromMap() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", 2);
        map.put("k3", 3);
        return SimpleImmutableMap.from(map);
    }

    static SimpleImmutableMap<String, Integer> fromList() {
        return SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);
    }

    @Test
    @DisplayName("should create mutable map that is not connected with immutable map")
    void toMutableMap() {
        // given
        final SimpleImmutableMap<String, Integer> immutableMap = SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);

        // when
        final Map<String, Integer> mutableMap = immutableMap.toMutableMap();

        // then
        assertThat(mutableMap).containsExactlyInAnyOrderEntriesOf(immutableMap);
        mutableMap.put("k4", 4);
        assertThat(mutableMap).containsEntry("k4", 4);
        assertThat(immutableMap).doesNotContainKey("k4");
    }

    @Test
    @DisplayName("should create new immutable map and put into it entry")
    void putToNew() {
        // given
        final SimpleImmutableMap<String, Integer> sourceMap = SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);

        // when
        final ImmutableMap<String, Integer> newMap = sourceMap.putToNew("k4", 4);

        // then
        assertThat(newMap).containsAllEntriesOf(sourceMap);
        assertThat(newMap).containsEntry("k4", 4);
        assertThat(sourceMap).doesNotContainKey("k4");
    }

    @Test
    @DisplayName("should create new immutable map and put into it all entries from map")
    void putAllToNew() {
        // given
        final SimpleImmutableMap<String, Integer> sourceMap = SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);
        final Map<String, Integer> addMap = new HashMap<>();
        addMap.put("k4", 4);
        addMap.put("k5", 5);
        addMap.put("k6", 6);

        // when
        final ImmutableMap<String, Integer> newMap = sourceMap.putAllToNew(addMap);

        // then
        assertThat(newMap).containsAllEntriesOf(sourceMap);
        assertThat(newMap).containsAllEntriesOf(addMap);
        assertThat(sourceMap).doesNotContainKeys(addMap.keySet().toArray(new String[0]));
    }

    @Test
    @DisplayName("should throw `NullPointerException` if given map is null")
    void putAllToNewNull() {
        // given
        final SimpleImmutableMap<String, Integer> sourceMap = SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);

        // when
        final ThrowableAssert.ThrowingCallable when = () -> sourceMap.putAllToNew(null);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("should create new immutable map and remove given key")
    void removeFromNew() {
        // given
        final SimpleImmutableMap<String, Integer> sourceMap = SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);

        // when
        final ImmutableMap<String, Integer> newMap = sourceMap.removeFromNew("k1");

        // then
        assertThat(sourceMap).containsAllEntriesOf(newMap);
        assertThat(newMap).doesNotContainKey("k1");
        assertThat(sourceMap).containsEntry("k1", 1);
    }

    @Test
    @DisplayName("should throw `IllegalArgumentException` if there are not odd number of parameters in `keyValues`")
    void fromListNotEven() {
        // when
        final ThrowableAssert.ThrowingCallable when = () -> SimpleImmutableMap.from(
                String.class, Integer.class, "k1");

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("should throw `IllegalArgumentException` if one of the keys has bad type")
    void fromListBadClassOfKey() {
        // when
        final ThrowableAssert.ThrowingCallable when = () -> SimpleImmutableMap.from(
                String.class, Integer.class, "k1", 1, new Object(), 2);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .hasMessageStartingWith("Key at index 2 is not type of `java.lang.String`! Key=")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("should throw `IllegalArgumentException` if one of the values has bad type")
    void fromListBadClassOfValue() {
        // when
        final ThrowableAssert.ThrowingCallable when = () -> SimpleImmutableMap.from(
                String.class, Integer.class, "k1", 1, "k2", "2");

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .hasMessage("Value at index 3 is not type of `java.lang.Integer`! Value=2")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("should have proper `toString()`")
    void toStringValue() {
        // given
        final SimpleImmutableMap<String, Integer> immutableMap = SimpleImmutableMap.from(
                String.class, Integer.class,
                "k1", 1,
                "k2", 2,
                "k3", 3);

        // when
        final String toString = immutableMap.toString();

        // then
        assertThat(toString).isEqualTo("SimpleImmutableMap[k1=1, k2=2, k3=3]");
    }

    @Test
    @DisplayName("should throw `NullPointerException` if `keyClass` is `null`")
    void fromListNullKeyClass() {
        // given

        // when
        final ThrowableAssert.ThrowingCallable when = () -> SimpleImmutableMap.from(null, Integer.class, "1", 2);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("should throw `NullPointerException` if `valueClass` is `null`")
    void fromListNullValueClass() {
        // given

        // when
        final ThrowableAssert.ThrowingCallable when = () -> SimpleImmutableMap.from(String.class, null, "1", 2);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("should throw `NullPointerException` if `keyValues` are `null`")
    void fromListNullKeyValues() {
        // given

        // when
        final ThrowableAssert.ThrowingCallable when = () -> SimpleImmutableMap.from(String.class, Integer.class, (Object[]) null);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("should create empty map if `keyValues` are empty")
    void fromListEmptyKeyValues() {
        // given

        // when
        final SimpleImmutableMap<String, Integer> map = SimpleImmutableMap.from(String.class, Integer.class);

        // then
        assertThat(map).isEmpty();
    }

    @Test
    @DisplayName("should throw `UnsupportedOperationException` when putting entry")
    void put() {
        // given
        final SimpleImmutableMap<String, Integer> map = SimpleImmutableMap.from(String.class, Integer.class);

        // when
        final ThrowableAssert.ThrowingCallable when = () -> map.put("k", 0);

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("should throw `UnsupportedOperationException` when removing key")
    void remove() {
        // given
        final SimpleImmutableMap<String, Integer> map = SimpleImmutableMap.from(String.class, Integer.class);

        // when
        final ThrowableAssert.ThrowingCallable when = () -> map.remove("k");

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("should throw `UnsupportedOperationException` when putting all entries")
    void putAll() {
        // given
        final SimpleImmutableMap<String, Integer> map = SimpleImmutableMap.from(String.class, Integer.class);

        // when
        final ThrowableAssert.ThrowingCallable when = () -> map.putAll(new HashMap<>());

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("should throw `UnsupportedOperationException` when clearing map ")
    void clear() {
        // given
        final SimpleImmutableMap<String, Integer> map = SimpleImmutableMap.from(String.class, Integer.class);

        // when
        final ThrowableAssert.ThrowingCallable when = map::clear;

        // then
        assertThatThrownBy(when)
                .hasNoCause()
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
