package pcd.lab05.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Barrier - to be implemented
 */
public class BarrierImpl implements Barrier {
	private final Lock mutex;
	private final Condition releaseWaiting;
	private final int nParticipants;
	private int waitingThreads;

	public BarrierImpl(int nParticipants) {
		this.nParticipants = nParticipants;
		this.mutex = new ReentrantLock();
		this.releaseWaiting = this.mutex.newCondition();
		this.waitingThreads = 0;
	}
	
	@Override
	public void hitAndWaitAll() throws InterruptedException {
		try {
			mutex.lock();
			if (waitingThreads < nParticipants - 1) {
				waitingThreads += 1;
				this.releaseWaiting.await();
			} else {
				this.waitingThreads = 0;
				this.releaseWaiting.signalAll();
			}
		} finally {
			mutex.unlock();
		}
	}
}
