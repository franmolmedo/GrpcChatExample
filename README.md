# GRPC - Chat example

## Introduction

The purpose of this demo is showing a full example of how creating a GRPC project with multiple agents working together. We have 3 agents:

-   A server, written in Java, which receives the messages from the clients and send the messages received to all the available clients.
-   Two clients, one written in Go and other one written in C#, acting as chat clients where you can enter a message and receive the message from the other users.

## Folder structure

The folder structure is as follows:

-   **Server** folder: Is where all files related Java solution are located.
-   **Client-GO** folder: The client written in Go.
-   **Client-C#** folder: Client written in C# using **_WPF_**.
-   **Proto** folder: Definition of our services using _protocol buffers_.

## How to start

-   Start the server
-   Run as many clients as you desire.

In each folder you can find a _README_ with the information about how to run the app and all dependencies that are needed to be installed in order to run the project properly.
