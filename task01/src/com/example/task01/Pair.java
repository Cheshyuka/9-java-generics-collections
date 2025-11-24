package com.example.task01;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T, K> {
    private final T first;
    private final K second;

    private Pair(T first, K second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public K getSecond(){
        return second;
    }

    public static <T, K> Pair<T, K> of(T first, K second){
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super K> consumer) {
        if (first != null && second != null)
            consumer.accept(first, second);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    public int hashCode() {
        return Objects.hash(first, second);
    }

}
