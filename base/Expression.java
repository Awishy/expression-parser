package expression.base;

@SuppressWarnings("ClassReferencesSubclass")
public interface Expression extends ToMiniString {
    int evaluate(int x);
}
