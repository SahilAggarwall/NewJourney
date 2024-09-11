docker run -d --name schema-registry -p 8081:8081 `
    -e SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS=PLAINTEXT://localhost:9092 `
    -e SCHEMA_REGISTRY_LISTENERS=http://0.0.0.0:8081 `
    -e SCHEMA_REGISTRY_HOST_NAME=localhost `
    confluentinc/cp-schema-registry:latest


    java -jar avro-tools-1.10.2.jar compile schema ./src/main/kotlin/com/fretron/avro userUpdate.avsc ./src/main/java\