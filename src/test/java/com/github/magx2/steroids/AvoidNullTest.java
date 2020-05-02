package com.github.magx2.steroids;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

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

    @Test
    @DisplayName("should return `Optional` of first element from objects")
    void tryFirstNonNullOneElements() {
        // when
        final Optional<String> firstNonNull = AvoidNull.tryFirstNonNull("x");

        // then
        assertThat(firstNonNull).contains("x");
    }

    @Test
    @DisplayName("should return `Optional` of second element from objects")
    void tryFirstNonNullMultipleElements() {
        // when
        final Optional<String> firstNonNull = AvoidNull.tryFirstNonNull(null, "x", null, "y");

        // then
        assertThat(firstNonNull).contains("x");
    }

    @Test
    @DisplayName("should return empty `Optional` if only passed null")
    void tryFirstNonNullOneNull() {
        // when
        final Optional<Object> firstNonNull = AvoidNull.tryFirstNonNull(null);

        // then
        assertThat(firstNonNull).isEmpty();
    }

    @Test
    @DisplayName("should return empty `Optional` if all objects was null")
    void tryFirstNonNullMultipleNulls() {
        // when
        final Optional<Object> firstNonNull = AvoidNull.tryFirstNonNull(null, null, null);

        // then
        assertThat(firstNonNull).isEmpty();
    }

    @Test
    @DisplayName("should return empty `Optional` if all objects was null")
    void tryFirstNonNullRestIsNull() {
        // when
        final Optional<Object> firstNonNull = AvoidNull.tryFirstNonNull(null, (Object[]) null);

        // then
        assertThat(firstNonNull).isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("should return given collection if it's not null")
    void collectionOrDefault() {
        // given
        Collection<String> collection = mock(Collection.class);

        // when
        final Collection<String> orDefault = AvoidNull.orDefault(collection);

        // then
        assertThat(orDefault).isEqualTo(collection);
    }

    @Test
    @DisplayName("should return empty collection if passed null")
    void collectionOrDefaultEmpty() {
        // when
        final Collection<String> orDefault = AvoidNull.orDefault((Collection<String>) null);

        // then
        assertThat(orDefault).isEmpty();
    }

    @Test
    @DisplayName("should return given list if it's not null")
    void listOrDefault() {
        // given
        List<String> list = asList("1", "2", "3");

        // when
        final List<String> orDefault = AvoidNull.orDefault(list);

        // then
        assertThat(orDefault).isEqualTo(list);
    }

    @Test
    @DisplayName("should return empty list if passed null")
    void listOrDefaultEmpty() {
        // when
        final List<String> orDefault = AvoidNull.orDefault((List<String>) null);

        // then
        assertThat(orDefault).isEmpty();
    }

    @Test
    @DisplayName("should return given set if it's not null")
    void setOrDefault() {
        // given
        Set<String> set = new HashSet<>(asList("1", "2", "3"));

        // when
        final Set<String> orDefault = AvoidNull.orDefault(set);

        // then
        assertThat(orDefault).isEqualTo(set);
    }

    @Test
    @DisplayName("should return empty set if passed null")
    void setOrDefaultEmpty() {
        // when
        final Set<String> orDefault = AvoidNull.orDefault((Set<String>) null);

        // then
        assertThat(orDefault).isEmpty();
    }

    @Test
    @DisplayName("should return given sorted set if it's not null")
    void sortedSetOrDefault() {
        // given
        SortedSet<String> sortedSet = new TreeSet<>(asList("1", "2", "3"));

        // when
        final SortedSet<String> orDefault = AvoidNull.orDefault(sortedSet);

        // then
        assertThat(orDefault).isEqualTo(sortedSet);
    }

    @Test
    @DisplayName("should return empty sorted set if passed null")
    void sortedSetOrDefaultEmpty() {
        // when
        final SortedSet<String> orDefault = AvoidNull.orDefault((SortedSet<String>) null);

        // then
        assertThat(orDefault).isEmpty();
    }

    @Test
    @DisplayName("should return given map if it's not null")
    void mapOrDefault() {
        // given
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", 2);
        map.put("k3", 3);

        // when
        final Map<String, Integer> orDefault = AvoidNull.orDefault(map);

        // then
        assertThat(orDefault).isEqualTo(map);
    }

    @Test
    @DisplayName("should return empty map if passed null")
    void mapOrDefaultEmpty() {
        // when
        final Map<String, Integer> orDefault = AvoidNull.orDefault((Map<String, Integer>) null);

        // then
        assertThat(orDefault).isEmpty();
    }

    @Test
    @DisplayName("should return given sorted map if it's not null")
    void sortedMapOrDefault() {
        // given
        SortedMap<String, Integer> map = new TreeMap<>();
        map.put("k1", 1);
        map.put("k2", 2);
        map.put("k3", 3);

        // when
        final Map<String, Integer> orDefault = AvoidNull.orDefault(map);

        // then
        assertThat(orDefault).isEqualTo(map);
    }

    @Test
    @DisplayName("should return empty sorted map if passed null")
    void sortedMapOrDefaultEmpty() {
        // when
        final SortedMap<String, Integer> orDefault = AvoidNull.orDefault((SortedMap<String, Integer>) null);

        // then
        assertThat(orDefault).isEmpty();
    }

    @Test
    @DisplayName("should return given stream if it's not null")
    void streamOrDefault() {
        // given
        Stream<String> stream = Stream.of("1", "2", "3");

        // when
        final Stream<String> orDefault = AvoidNull.orDefault(stream);

        // then
        assertThat(orDefault).isEqualTo(stream);
    }

    @Test
    @DisplayName("should return empty stream if passed null")
    void streamOrDefaultEmpty() {
        // when
        final Stream<String> orDefault = AvoidNull.orDefault((Stream<String>) null);

        // then
        assertThat(orDefault).isEmpty();
    }
}
