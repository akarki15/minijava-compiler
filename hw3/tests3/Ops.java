public class Ops {

    static int f() {}

    public static void main (String[] args) {

	int a;
	String s;
	int[] b;
	boolean bb;

	a = a*a;
	a = a-a;
	a = a%a;
	a = a*a;
	a = a/a;

	s = s + s;
	s = s + a;
	s = s + null;
	s = s + b;
	s = s + bb;

	bb = a < a;
	bb = a <= a;
	bb = a > a;
	bb = a >= a;
	bb = a == a;
	bb = a != a;
	
	bb = s == null;
	bb = b != null;

	bb = bb || bb;
	bb = bb && bb;
	bb = (bb == false);
	bb = (bb == true);

	System.out.print(f());

	b = b = null;
    }
}

