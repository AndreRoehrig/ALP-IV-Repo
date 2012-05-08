package AliceAndBob;


import java.util.concurrent.atomic.AtomicBoolean;


class Bob extends Thread {
		
	public static AtomicBoolean flag1 = new AtomicBoolean(), flag2 = new AtomicBoolean();
	public int in_yard = 0;				// wie häufig war der Hund im Hof?
	long msecs;							// zur Zeitmessung (Zeitlimit)
	public static boolean in_ct = false;// wird gesetzt, wenn Thread in CT ist
	
	public Bob() {
		
		msecs = System.currentTimeMillis();

	}
	
	public void run() {
		
		while(System.currentTimeMillis() - msecs < 5000) {
			flag1.set(true);
		
			if(Alice.flag2.get() == false)
				flag2.set(true);
			else
				flag2.set(false);

			while(!(Alice.flag1.get() == false || Alice.flag2.get() == flag2.get()));
		
			// Anfang der kritischen Sektion
			in_ct = true;

			System.out.printf("Bob's dog in the yard!\n");
			++in_yard;

			/* Test auf gegenseitigen Ausschluss */
			long t = System.currentTimeMillis();
			while(System.currentTimeMillis() - t < 500)
				if(Alice.in_ct) throw new RuntimeException();
			
			in_ct = false;
			// Ende der kritischen Sektion
			
			flag1.set(false);
			
			Thread.yield();
			
		}
		
	}
		
}