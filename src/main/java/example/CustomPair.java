package example;

public class CustomPair<A, B> {
    private final A first;
    private final B second;

    public CustomPair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public static <A, B> CustomPair<A, B> of(A first, B second) {
        return new CustomPair<>(first, second);
    }
}