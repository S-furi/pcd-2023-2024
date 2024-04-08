package pcd.lab06.vt;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TestScalabilityVT2 {

	public static void main(String[] args) {
		var t0 = System.currentTimeMillis();
		try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
		    IntStream.range(0, 100_000).forEach(i -> {
		        executor.submit(() -> {
		            Thread.sleep(Duration.ofSeconds(1));
		            return i;
		        });
		    });
		}
		var t1 = System.currentTimeMillis();
		System.out.println("Time elapsed: " + (t1 - t0));
		
	}

}
