package pcd.lab06.executors.deadlock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExecDeadlock {

	/**
	 * I task disaccoppiano il livello logico dal livello fisico,
	 * quindi se implemento meccanismi di sincronizzazione fisici (i.e. barrier),
	 * allora blocco ogni thread fisico associato ad ogni task.
	 * I task devono essere indipendenti tra loro, e non mettere mai meccanismi di sincornizzazione
	 * logica tra i task.
	 *
	 * Questo a meno che i task non debbano andare in sezione critica su memoria condivisa,
	 * l'importante è che i monitor non abbiano meccanismi di sincornizzazione e implementino
	 * meri meccanismi di sezione critica (e.g. lock).
	 */
	public static void main(final String[] args) throws Exception {

		int nTasks = 100; 
		int nThreads = Runtime.getRuntime().availableProcessors() + 1;

		try (final ExecutorService exec = Executors.newFixedThreadPool(nThreads)) {
			final CyclicBarrier barrier = new CyclicBarrier(nTasks);

			for (int i = 0; i < nTasks; i++) {
				exec.execute(new MyTask("task-" + i, barrier));
			}

			exec.shutdown();
			exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		}
	}
}
