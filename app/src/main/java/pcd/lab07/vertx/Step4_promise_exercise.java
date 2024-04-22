package pcd.lab07.vertx;

import io.vertx.core.*;

import java.util.Random;


/**
 * Exercise
 * 
 * -- implement an async protected method getDelayedRandom(int delay)
 *    that returns a random value between 0 and 1 after the specified
 *    amount of time
 *    
 * -- in the "start" method, test the behaviour of the method by using it.
 * 
 */
class VerticleWithPromise extends AbstractVerticle {

	@Override
	public void start() {
		log("started.");

		final var f = getDelayedRandom(1000);
		f.onSuccess(res -> log("result is: " + res));
	}

	protected Future<Double> getDelayedRandom(final int delay) {
		final Promise<Double> p = Promise.promise();
		this.getVertx().setTimer(delay, id -> {
			p.complete(new Random().nextDouble());
		});
		return p.future();
	}

	private void log(String msg) {
		System.out.println("[REACTIVE AGENT]["+Thread.currentThread()+"]" + msg);
	}
}

public class Step4_promise_exercise {
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VerticleWithPromise());
	}
}

