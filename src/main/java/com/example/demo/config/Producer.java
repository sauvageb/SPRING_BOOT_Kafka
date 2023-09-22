//package com.example.demo.config;
//
//import com.github.javafaker.Faker;
//import org.springframework.boot.context.event.ApplicationStartedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//import java.util.stream.Stream;
//
//
//@Component
//public class Producer {
//
//    private KafkaTemplate<Integer, String> kafkaTemplate;
//
//    public Producer(KafkaTemplate<Integer, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    private Faker faker;
//
//    // Méthode appelée lors du démarrage de l'application
//    @EventListener(ApplicationStartedEvent.class)
//    public void generate() {
//        // Méthode appelée lors du démarrage de l'application.
//        faker = Faker.instance();
//        // Initialise une instance de Faker pour la génération de données fictives.
//        final Flux<Long> interval = Flux.interval(Duration.ofMillis(1_000));
//        // Crée un flux d'intervalles réguliers de 1 seconde.
//        final Flux<String> quotes = Flux.fromStream(Stream.generate(() -> faker.hobbit().quote()));
//        // Crée un flux de citations fictives générées par Faker.
//        Flux.zip(interval, quotes)
//                // Combine les flux d'intervalles et de citations en couples (interval, quote).
//                .map(it -> kafkaTemplate.send("hobbits", faker.random().nextInt(42), it.getT2())).blockLast();
//        // Pour chaque couple, envoie un message à un topic Kafka ("hobbits") avec une clé aléatoire et la citation comme valeur, puis bloque jusqu'à ce que tous les messages soient envoyés.
//    }
//
//}
