package com.logflow.log_consumer.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexResponse;

@Component
public class LogConsumer {

    private final ElasticsearchClient elasticsearchClient;

    public LogConsumer(ElasticsearchClient elasticsearchClient) {
    this.elasticsearchClient = elasticsearchClient;
}

    @KafkaListener(topics = "order-logs")
    
    public void processMessage(String content) throws IOException {
        try {
            Map<String, Object> doc = new HashMap<>();
            doc.put("message", content);
            doc.put("timestamp", java.time.Instant.now().toString());

            IndexResponse response = elasticsearchClient.index(i -> i
                    .index("order-logs")
                    .document(doc));
                    
            System.out.println(response);
            }
        catch ( ElasticsearchException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
