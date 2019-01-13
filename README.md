# ERP/CRM Microservices System

This repository features the whole system gathering micro applications built using Spring Cloud architecture.

A Wiki documentation is on its way...


## Prerequesites

### Kafka / Zookeeper

A Kafka server along with Zookeeper must be running or be available so you can communicate via events between services.

For handling the configuration, a **Zookeeper** instance must also be running, see [Zookeeper](https://hub.docker.com/_/zookeeper/)

> $ docker run --name rhm-zookeeper --restart always -d zookeeper

You may also decide to run Zookeeper on your own following the [documentation](https://zookeeper.apache.org/doc/current/zookeeperStarted.html).


We choose to use the Zookeeper image rather than the combinaison of Kafka/Zookeeper to be sure we are dealing with the latest releases.

Now [Kafka](https://kafka.apache.org/intro) :

And the details for running a Docker image : [Bitnami/Docker](https://hub.docker.com/r/bitnami/kafka/)

> $ docker run -d --name kafka-server -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e ALLOW_PLAINTEXT_LISTENER bitnami/kafka:latest

Please read carefully the readme to get a better understanding of how to run and use the container - here we in development environment.



