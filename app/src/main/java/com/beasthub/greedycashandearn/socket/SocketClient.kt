package com.example.networking.socket

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

class SocketClient {
    private val serverAddress: String
    private val serverPort: Int

    constructor(serverAddress: String, serverPort: Int) {
        this.serverAddress = serverAddress
        this.serverPort = serverPort
    }

    fun startClient() {
        try {
            val socket = Socket(serverAddress, serverPort)

            val outputStream = socket.getOutputStream()
            val out = PrintWriter(OutputStreamWriter(outputStream, "UTF-8"), true)

            val inputStream = socket.getInputStream()
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

            // Send data to the server
            out.println("Hello, Server!")

            // Receive data from the server
            val response = reader.readLine()
            println("Server response: $response")

            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun main() {
    val client = SocketClient("127.0.0.1", 8080)
    client.startClient()
}
