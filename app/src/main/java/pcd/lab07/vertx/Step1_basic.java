package pcd.lab07.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import java.io.*;

public class Step1_basic {

	public static void main(String[] args) {
		final Vertx vertx = Vertx.vertx();
		final FileSystem fs = vertx.fileSystem();

		log("doing the async call... ");
		
		/* version 4.X - future (promise) based API */
		final Future<Buffer> fut = fs.readFile("settings.gradle.kts");
		fut.onComplete((AsyncResult<Buffer> res) -> {
			log("BUILD \n" + res.result().toString().substring(0,160));
		});

		log("async call done. Waiting some time... ");

		try {
			Thread.sleep(1000);
		} catch (Exception ex) {}
		
		log("done");
	}
	
	private static void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}
}

