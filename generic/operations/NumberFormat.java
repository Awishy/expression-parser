package expression.generic.operations;

public interface NumberFormat<T> {
    T add(T x, T y);
    T subtract(T x, T y);
    T multiply(T x, T y);
    T divide(T x, T y);
    T minus(T x);
    T valueOf(int x);
    T min(T x, T y);
    T max(T x, T y);
    T count(T x);
}
