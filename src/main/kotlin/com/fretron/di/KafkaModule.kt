package com.fretron.di

import com.fretron.services.EmailConsumer
import com.fretron.services.SignupProducer
import dagger.Module
import dagger.Provides
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import javax.inject.Singleton
import java.util.Properties

@Module
class KafkaModule {

    @Provides
    @Singleton
    fun provideKafkaProducer(): KafkaProducer<String, String> {
        val props = Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        }
        return KafkaProducer(props)
    }

    @Provides
    @Singleton
    fun provideSignupProducer(kafkaProducer: KafkaProducer<String, String>): SignupProducer {
        return SignupProducer(kafkaProducer)
    }

    @Provides
    @Singleton
    fun provideKafkaConsumer(): KafkaConsumer<String, String> {
        val props = Properties().apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
            put(ConsumerConfig.GROUP_ID_CONFIG, "email-consumer-group")
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        }
        val consumer = KafkaConsumer<String, String>(props)
        consumer.subscribe(listOf("welcome-topic"))
        return consumer
    }

    @Provides
    @Singleton
    fun provideEmailConsumer(kafkaConsumer: KafkaConsumer<String, String>): EmailConsumer {
        return EmailConsumer(kafkaConsumer)
    }
}
