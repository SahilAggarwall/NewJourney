package com.fretron.services

import org.apache.kafka.clients.consumer.KafkaConsumer
import javax.inject.Inject

class EmailConsumer @Inject constructor(private val consumer: KafkaConsumer<String, String>){



    fun listenForEmails() {
        while (true) {
            val records = consumer.poll(1000)
            println("listening")
            for (record in records) {
                sendEmail(record.value())
            }
        }
    }

    private fun sendEmail(email: String) {
        println("Sending welcome email to $email")
        // Logic to send email goes here
    }
}
