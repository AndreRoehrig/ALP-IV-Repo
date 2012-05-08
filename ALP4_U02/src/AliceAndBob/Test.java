package AliceAndBob;


class Test {
	
	public static void main(String[] args) {
		
		Alice alice = new Alice();
		Bob bob = new Bob();
		
		alice.start();
		bob.start();

		try {
			alice.join();
			bob.join();
		} catch (InterruptedException e) {}
		
		System.out.printf("Alices Hund im Hof: %d mal\nBobs Hund im Hof: %d mal\n", alice.in_yard, bob.in_yard);
		
	}
	
}