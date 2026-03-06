# CloudDrive — Laboratório de Microserviços com Spring Boot

CloudDrive é um projeto de estudo que simula uma plataforma de armazenamento de arquivos semelhante a um **Dropbox simplificado**.
O objetivo principal é explorar o ecossistema **Spring Boot** e arquiteturas modernas de backend.

O projeto evolui gradualmente de um serviço simples para uma arquitetura completa com **microserviços, mensageria, cache, observabilidade e storage compatível com S3**.

A infraestrutura local é executada via **Docker Compose**, permitindo simular um ambiente próximo ao encontrado em sistemas reais.

---

# Arquitetura da Infraestrutura

O ambiente de desenvolvimento inclui os seguintes componentes:

* **PostgreSQL** → banco de dados relacional
* **Redis** → cache e armazenamento temporário
* **Kafka + Zookeeper** → mensageria e eventos
* **MinIO** → armazenamento de objetos compatível com S3
* **Prometheus** → coleta de métricas
* **Grafana** → dashboards e visualização de métricas

Ferramentas de administração também são disponibilizadas:

* **pgAdmin** → interface gráfica para PostgreSQL
* **Redis Insight** → gerenciamento do Redis
* **Kafka UI** → visualização de tópicos e consumidores Kafka
* **MinIO Console** → gerenciamento de buckets e objetos

---

# Estrutura do Repositório

```
clouddrive
│
├─ docker
│  ├─ docker-compose.yml
│  └─ prometheus.yml
│
├─ services
│  ├─ auth-service
│  ├─ file-service
│  ├─ user-service
│  └─ ...
│
├─ libs
│  ├─ common
│  ├─ security
│  ├─ events
│  └─ storage
│
└─ README.md
```

* **docker/** → infraestrutura local
* **services/** → microserviços Spring Boot
* **libs/** → bibliotecas compartilhadas entre serviços

---

# Requisitos

Antes de rodar o projeto, instale:

* Docker
* Docker Compose

No Windows recomenda-se usar **Docker Desktop**.

---

# Como iniciar a infraestrutura

Entre na pasta de infraestrutura:

```bash
cd docker
```

Inicie os containers:

```bash
docker compose up -d
```

Verifique se todos os containers estão rodando:

```bash
docker ps
```

Para parar tudo:

```bash
docker compose down
```

Para remover também os volumes:

```bash
docker compose down -v
```

---

# Interfaces Web disponíveis

Após iniciar os containers, as seguintes interfaces estarão disponíveis:

| Serviço       | URL                   |
| ------------- | --------------------- |
| Kafka UI      | http://localhost:8080 |
| MinIO Console | http://localhost:9001 |
| Prometheus    | http://localhost:9090 |
| Grafana       | http://localhost:3000 |
| pgAdmin       | http://localhost:5050 |
| Redis Insight | http://localhost:5540 |

---

# Credenciais padrão

## MinIO

```
User: admin
Password: admin123
```

Endpoint S3:

```
http://localhost:9000
```

---

## pgAdmin

```
Email: admin@admin.com
Password: admin
```

Ao adicionar um servidor PostgreSQL:

```
Host: postgres
User: postgres
Password: postgres
Port: 5432
```

---

# Serviços da infraestrutura

## PostgreSQL

Banco relacional principal usado pelos serviços.

Porta:

```
5432
```

---

## Redis

Utilizado para cache e dados temporários.

Porta:

```
6379
```

---

## Kafka

Sistema de mensageria usado para comunicação assíncrona entre serviços.

Porta:

```
9092
```

Interface administrativa disponível em **Kafka UI**.

---

## MinIO

Armazenamento de objetos compatível com S3 utilizado para arquivos do sistema.

Portas:

```
9000 → API S3
9001 → Console Web
```

---

## Prometheus

Responsável por coletar métricas dos serviços Spring Boot via endpoint:

```
/actuator/prometheus
```

---

## Grafana

Utilizado para criar dashboards e visualizar métricas coletadas pelo Prometheus.

---

# Objetivo do projeto

Este projeto serve como um laboratório para explorar:

* Spring Boot
* Spring Security
* Spring Data
* Arquitetura de microserviços
* Event-driven architecture
* Kafka
* Redis
* Observabilidade (Prometheus + Grafana)
* Storage S3 (MinIO)
* Docker e infraestrutura local
