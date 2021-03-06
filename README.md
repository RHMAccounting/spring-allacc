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
   
### Keycloak

As for the Kafka and Zookeeper instances, we choose to run a Docker image featuring Keycloak server.
Keycloak is an Open Source identity and access management (see [Keycloak website](https://www.keycloak.org/)). 
This solution has been chosen as it is a reference in its field and it allows us to concentrate on the core business of our application.

You may also run Okta or whatever identity/access management tool.

Here we use the Docker image from [jboss/Keycloak](https://hub.docker.com/r/jboss/keycloak).

Let's first define a network for our instance :

> $ docker network create keycloak-network

Then run a Postgresql server using Docker as well (or whatever DB server ) :

> docker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres

And finally :

> $ docker run --name keycloak -e DB_VENDOR=postgres -e JDBC_PARAMS='connectTimeout=30' -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=123456 --net keycloak-network jboss/keycloak


To quickly get started wtih Keycloak, follow the steps :

   1. Add a new Realm (ie : RHM)
   2. Add a client (ie : rhm-gate)
   3. Once the settings are available, fill in a valid redirect uri to match the future Zuul gateway (ie : http://localhost:8090)
   4. Set the access type to bearer-only on the client page and click save
   5. Now head to the credentials tab and get the secret (you will need it to set up authentication from within your application)
   6. Create a User role
   7. add a user and give him a role
   
For more information, refer to Keycloak documentation.

### Zipkin

For proper debugging, we need a way to trace requests across our micro services, here we use Zipkin and a docker image :

> $ docker run -d -p 9411:9411 openzipkin/zipkin

