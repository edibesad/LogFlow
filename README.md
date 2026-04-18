# LogFlow
![](https://tracker.edibesad.com/count.svg?ref=LogFlow&v=2)
**TR** | [EN](#en)

---

## TR

Dağıtık log toplama ve izleme sistemi. Mikroservislerden üretilen loglar Kafka üzerinden taşınarak Elasticsearch'e yazılır ve Kibana aracılığıyla görselleştirilir.

### Mimari

```
order-service → Kafka → log-consumer → Elasticsearch → Kibana
```

### Servisler

- **order-service** — Sipariş endpoint'i sunan Spring Boot servisi. Her istekte Kafka'ya log mesajı üretir.
- **log-consumer** — Kafka topic'ini dinleyen Spring Boot servisi. Gelen mesajları Elasticsearch'e index'ler.

### Teknolojiler

| Katman | Teknoloji |
|--------|-----------|
| Backend | Java 21, Spring Boot 4 |
| Mesajlaşma | Apache Kafka (KRaft modu) |
| Arama & Depolama | Elasticsearch 8.13 |
| Görselleştirme | Kibana 8.13 |
| Konteyner | Docker, Docker Compose |
| Orkestrasyon | Kubernetes (Deployment, Service) |

### Neden Kafka?

Servisler arasındaki sıkı bağımlılığı kaldırmak için Kafka tercih edildi. `order-service` yalnızca mesajı yazar; tüketici taraf ne olursa olsun etkilenmez. Kafka mesajları diske yazdığı için geçici consumer kesintilerinde veri kaybı yaşanmaz.

### Çalıştırma

**Docker Compose ile:**
```bash
docker compose up -d
```

**Kubernetes ile:**
```bash
kubectl apply -f k8s/
```

Servisler:
- `localhost:8080/orders` — order-service
- `localhost:5601` — Kibana
- `localhost:9200` — Elasticsearch

---

## EN

<a name="en"></a>

Distributed log aggregation and monitoring system. Logs produced by microservices are transported via Kafka, written to Elasticsearch, and visualized through Kibana.

### Architecture

```
order-service → Kafka → log-consumer → Elasticsearch → Kibana
```

### Services

- **order-service** — Spring Boot service exposing an order endpoint. Produces a Kafka log message on every request.
- **log-consumer** — Spring Boot service listening to the Kafka topic. Indexes incoming messages into Elasticsearch.

### Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Java 21, Spring Boot 4 |
| Messaging | Apache Kafka (KRaft mode) |
| Search & Storage | Elasticsearch 8.13 |
| Visualization | Kibana 8.13 |
| Container | Docker, Docker Compose |
| Orchestration | Kubernetes (Deployment, Service) |

### Why Kafka?

Kafka was chosen to decouple services. `order-service` only writes a message; it is unaffected by whatever happens on the consumer side. Since Kafka persists messages to disk, no data is lost during temporary consumer outages.

### Running

**With Docker Compose:**
```bash
docker compose up -d
```

**With Kubernetes:**
```bash
kubectl apply -f k8s/
```

Endpoints:
- `localhost:8080/orders` — order-service
- `localhost:5601` — Kibana
- `localhost:9200` — Elasticsearch
