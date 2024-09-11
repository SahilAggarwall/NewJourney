package com.fretron.services

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration

class EmailConsumer(private val kafkaConsumer: KafkaConsumer<String, String>) {

    fun startConsuming() {
        Thread {
            while (true) {
                val records: ConsumerRecords<String, String> = kafkaConsumer.poll(Duration.ofSeconds(1))
                records.forEach { record ->
                    val email = record.key()
                    val message = record.value()
                    // Process the email and message here (e.g., send the email)
                    println("Received message: $message for email: $email")
                }
            }
        }.start()
    }
}
