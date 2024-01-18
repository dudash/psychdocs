package org.dudash.psychdocs;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.util.stream.Stream;

@ApplicationScoped
public class ReactiveMessagingApp {

    @Inject
    @Channel("query-out")
    Emitter<String> emitter;

    /**
     * Sends message to the "query-out" channel, can be used from a JAX-RS resource or any bean of your application.
     * Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        Stream.of("Hello", "with", "SmallRye", "reactive", "message").forEach(string -> emitter.send(string));
    }

    /**
     * Consume the message from the "query-in" channel, uppercase it and send it to the uppercase channel.
     * Messages come from the broker.
     **/
    @Incoming("query-in")
    @Outgoing("uppercase")
    public Message<String> toUpperCase(Message<String> message) {
        return message.withPayload(message.getPayload().toUpperCase());
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("uppercase")
    public void sink(String word) {
        System.out.println(">> " + word);
    }
}
