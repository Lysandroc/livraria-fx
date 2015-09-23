package thread;

public class TesteThread {
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Rodando em paralelo.");
			}
		}).start();
		
//		new Thread(() -> {
//			System.out.println("Rodando em paralelo.");
//		}).start();
		
		System.out.println("terminei de rodar o ain!");
	}
}
