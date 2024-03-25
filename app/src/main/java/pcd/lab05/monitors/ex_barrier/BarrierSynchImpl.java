package pcd.lab05.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Barrier - to be implemented
 */
public class BarrierSynchImpl implements Barrier {
	private final int nParticipants;
	private int waitingThreads;

	public BarrierSynchImpl(int nParticipants) {
		this.nParticipants = nParticipants;
		this.waitingThreads = 0;
	}
	
	@Override
	public synchronized void hitAndWaitAll() throws InterruptedException {
		waitingThreads++;
		if (waitingThreads == nParticipants) {
			this.notifyAll();
		} else {
			while (waitingThreads < nParticipants) {
				this.wait();
			}
		}
	}
}
