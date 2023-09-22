package com.example.demo.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.BUFFER_MEMORY_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-60py3.europe-west9.gcp.confluent.cloud:9092");
        props.put(RETRIES_CONFIG, 0);
        props.put(BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, String.format(
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=CYE5TUBR3DDTDIE2 " + "password=uThxDC1duqm0UjjmiDqOcdUaCE1xP1jFW9qvkR2OdjcDVByBloOPFKHReVjEtqqO;", PlainLoginModule.class.getName(), "username", "password"
        ));
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

//    @Bean
//    public ProducerFactory<Integer, String> robinProducerFactory() {
//        // Crée un objet de configuration des propriétés du producteur Kafka.
//        Map<String, Object> props = new HashMap<>();
//        // Configure l'adresse des serveurs Kafka à utiliser pour le démarrage.
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-60py3.europe-west9.gcp.confluent.cloud:9092");
//        // Configure le nombre de tentatives de renvoi en cas d'échec.
//        props.put(RETRIES_CONFIG, 0);
//        // Configure la mémoire tampon utilisée pour les données à envoyer.
//        props.put(BUFFER_MEMORY_CONFIG, 33554432);
//        // Configure la classe de sérialisation des clés des messages Kafka.
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
//        // Configure la classe de sérialisation des valeurs des messages Kafka.
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        // Configure la taille maximale des requêtes que le producteur peut envoyer.
//        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
//        // Configure le mécanisme d'authentification SASL à utiliser (PLAIN).
//        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
//        // Configure les informations d'authentification pour SASL (nom d'utilisateur et mot de passe).
//        props.put(SaslConfigs.SASL_JAAS_CONFIG, String.format(
//                "org.apache.kafka.common.security.plain.PlainLoginModule required username=CYE5TUBR3DDTDIE2 " + "password=uThxDC1duqm0UjjmiDqOcdUaCE1xP1jFW9qvkR2OdjcDVByBloOPFKHReVjEtqqO;", PlainLoginModule.class.getName(), "username", "password"
//        ));
//        // Configure le protocole de sécurité à utiliser (SASL_SSL)
//        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
//        // Crée et retourne une fabrique de producteurs Kafka avec la configuration spécifiée.
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<Integer, String> kafkaRobinTemplate() {
//        return new KafkaTemplate<>(robinProducerFactory());
//    }

}
