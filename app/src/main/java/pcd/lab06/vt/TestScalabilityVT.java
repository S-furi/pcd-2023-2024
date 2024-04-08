package pcd.lab06.vt;

import java.time.Duration;
import java.util.ArrayList;

public class TestScalabilityVT {

	public static void main(String[] args) {
		var t0 = System.currentTimeMillis();
		var list = new ArrayList<Thread>();
		for (var i = 0; i < 100_000; i++) {
			
			final Thread t = Thread.ofVirtual().unstarted(() -> {
				try {
					Thread.sleep(Duration.ofSeconds(1));
				} catch (final Exception ex) {
					ex.printStackTrace(System.err);
				}
			});
			t.start();
			list.add(t);
		}
		
		list.forEach(t -> {
			try {
				t.join();
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			};
		});

		var t1 = System.currentTimeMillis();
		System.out.println("Time elapsed: " + (t1 - t0));

	}

}
