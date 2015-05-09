public class Bad17 {

    static void f(String s, int[] a) {}

    static void f(int[] a, String s) {}

    public static void main (String[] args) {

	f(null, null);
    }
}

