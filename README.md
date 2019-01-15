# ERP/CRM Microservices System

This repository features the whole system gathering micro applications built using Spring Cloud architecture.

A Wiki documentation is on its way...


## Prerequesites

### Kafka / Zookeeper

A Kafka server along with Zookeeper must be running or be available so you can communicate via events between services.

For handling the configuration, a **Zookeeper** instance must also be running, see [Zookeeper](https://hub.docker.com/_/zookeeper/)

> $ docker run --name rhm-zookeeper --restart always -d zookeeper

Use the -p flag if you are running your application outside of the container (-p 2181:2181).

You may also decide to run Zookeeper on your own following the [documentation](https://zookeeper.apache.org/doc/current/zookeeperStarted.html).


We choose to use the Zookeeper image rather than the combinaison of Kafka/Zookeeper to be sure we are dealing with the latest releases.

Now [Kafka](https://kafka.apache.org/intro) :

And the details for running a Docker image : [Bitnami/Docker](https://hub.docker.com/r/bitnami/kafka/)

> $ docker run -d --name kafka-server -e KAFKA_ZOOKEEPER_CONNECT=192.168.99.100:2181 -e ALLOW_PLAINTEXT_LISTENER=yes bitnami/kafka:latest

Replace the IP address by the Zookeeper service name or IP of container depending on your configuration.

Follow the console logs to see what is going on with your services before going any further.

Please read carefully the readme files to get a better understanding of how to run and use the container - here we are in development environment.

*Note :*
    Bitnami also provides a Docker image with both Zookeeper and Kafka, you are free to use it.
   
   
