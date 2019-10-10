package com.axel.concurrency.thread.interupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/10/9
 */
public class CloseResource {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(8080);
		InputStream inputStream = new Socket("localhost", 8080).getInputStream();
		service.execute(new IOBlocked(inputStream));
		service.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Shutting down all threads");
		service.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + inputStream.getClass().getName());
		inputStream.close(); // Releases blocked thread
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + System.in.getClass().getName());
		System.in.close(); // Releases blocked thread
	}
}