package pcd.lab05.monitors.ex_latch;

public interface Latch {

	/**
	 * Signal when current worker has finished.
	 * Do not block current thread.
	 */
	void countDown();


	/**
	 * Called by blocked thread, wainting for others to finish their job:
	 * they'll notify the blocked thread calling countDown.
	 *
	 * @throws InterruptedException
	 */
	void await() throws InterruptedException;
}
