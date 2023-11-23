package com.example.networking.socket

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class SocketServer {
    private val serverPort: Int

    constructor(serverPort: Int) {
        this.serverPort = serverPort
    }

    fun startServer() {
        try {
            val serverSocket = ServerSocket(serverPort)
            println("Server listening on port $serverPort")

            while (true) {
                val clientSocket = serverSocket.accept()

                val inputStream = clientSocket.getInputStream()
                val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

                val outputStream = clientSocket.getOutputStream()
                val out = PrintWriter(OutputStreamWriter(outputStream, "UTF-8"), true)

                val clientMessage = reader.readLine()
                println("Received from client: $clientMessage")

                // Send a response back to the client
                out.println("Hello, Client!")

                clientSocket.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun main() {
    val server = SocketServer(8080)
    server.startServer()
}
