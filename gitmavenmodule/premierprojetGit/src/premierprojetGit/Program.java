package premierprojetGit;

public class Program {

	public static void main(String[] args) {
		System.out.println("hello world");
		System.out.println("autre essai");
		System.out.println(faitcalcul(9));
	}
	
	public static int faitcalcul(int value) {
		return (int)(value / 2.0 + 0.5);
	}

}
