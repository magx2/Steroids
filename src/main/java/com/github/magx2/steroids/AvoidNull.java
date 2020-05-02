package com.github.magx2.steroids;

import jdk.internal.jline.internal.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public final class AvoidNull {
    /**
     * This is an utils class. Never instantiate it.
     */
    private AvoidNull() {
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> T firstNonNull(@Nullable T first, @Nullable T... rest) {
        if (first != null) {
            return first;
        } else if (rest != null) {
            for (T object : rest) {
                if (object != null) {
                    return object;
                }
            }
        }
        throw new NullPointerException("Everything was null!");
    }

    @SafeVarargs
    public static <T> Optional<T> tryFirstNonNull(@Nullable T first, @Nullable T... rest) {
        if (first != null) {
            return Optional.of(first);
        } else if (rest != null) {
            for (T object : rest) {
                if (object != null) {
                    return Optional.of(object);
                }
            }
        }
        return Optional.empty();
    }
}
