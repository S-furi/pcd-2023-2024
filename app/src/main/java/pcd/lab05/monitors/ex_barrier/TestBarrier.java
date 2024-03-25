package pcd.lab05.monitors.ex_barrier;

import java.util.ArrayList;
import java.util.List;

public class TestBarrier {

	public static void main(String[] args) {
		
		int nWorkers = 10;

		/* this barrier is not working */
		Barrier barrierWithLocks = new BarrierImpl(nWorkers);
		Barrier barrierWithSynch = new BarrierSynchImpl(nWorkers);
		
		List<Worker> workers = new ArrayList<>();
		List<Worker> otherWorkers = new ArrayList<>();
		for (int i = 0; i < nWorkers; i++) {
			workers.add(new Worker("[Locks] Worker-"+i, barrierWithLocks));
			otherWorkers.add(new Worker("[Synch] Worker-"+i, barrierWithSynch));
		}

		workers.forEach(Thread::start);
		otherWorkers.forEach(Thread::start);
	}
}
