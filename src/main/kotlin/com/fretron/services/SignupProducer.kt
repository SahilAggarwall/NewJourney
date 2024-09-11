package com.fretron.services

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*
import javax.inject.Inject

class SignupProducer @Inject constructor(private val producer: KafkaProducer<String, String>){

    fun sendWelcomeEmailMessage(email: String) {
        val record = ProducerRecord<String, String>("welcome-email-topic", email)
        producer.send(record)
    }
}
