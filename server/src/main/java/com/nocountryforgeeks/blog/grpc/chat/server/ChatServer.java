package com.nocountryforgeeks.blog.grpc.chat.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ChatServer {
	public static void main(String [] args) throws IOException, InterruptedException {
		System.out.println("Hello! The server is running");
		
		Server server = ServerBuilder
				.forPort(8080)
				.addService(new ChatServiceImpl())
				.build();
		
		server.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Received shutdown request");
			server.shutdown();
			System.out.println("Shutdown resolved");
		}));
		
		server.awaitTermination();
	}
}
