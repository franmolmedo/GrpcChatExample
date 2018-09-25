package com.nocountryforgeeks.blog.grpc.chat.server;

import java.util.LinkedHashSet;

import io.grpc.stub.StreamObserver;

import com.nocountryforgeeks.blog.grpc.chat.ChatMessage;
import com.nocountryforgeeks.blog.grpc.chat.ChatServiceGrpc.ChatServiceImplBase;

public class ChatServiceImpl extends ChatServiceImplBase{
	
	private static LinkedHashSet<StreamObserver<ChatMessage>> observers = new LinkedHashSet<StreamObserver<ChatMessage>>();
	
	@Override
	public StreamObserver<ChatMessage> chat(StreamObserver<ChatMessage> responseObserver) {
		observers.add(responseObserver);
		
		return new StreamObserver<ChatMessage>() {

			@Override
			public void onNext(ChatMessage value) {
				ChatMessage messageToSend = ChatMessage.newBuilder()
						.setMessage(value.getMessage())
						.setFrom(value.getFrom())
						.build();
				System.out.println("Message received from: " + value.getFrom());
				observers.stream().forEach(observer -> observer.onNext(messageToSend));
			}

			@Override
			public void onError(Throwable t) {
				observers.remove(responseObserver);
			}

			@Override
			public void onCompleted() {
				observers.remove(responseObserver);
			}			
		};	
	}
} 
