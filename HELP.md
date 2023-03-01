## Useful Commands

### Download dependencies, compile, create artifacts

```
./mvnw package
```

### Run Docker dependencies

```shell
cd dog-service
docker compose up
```



### Start dog-service

```shell
./mvnw -pl dog-service spring-boot:run
```



### Start dog-client

```shell
./mvnw -pl dog-client spring-boot:run
```



### Start load-generator

```shell
./mvnw -pl load-generator gatling:test
```

If you want to modify the duration of the simulation, go to `DogsSimulation.java` and edit the `duration` variable.



### Native compile

This can take a while...

```shell
./mvnw -pl dog-service -Pnative native:compile
```


## Useful links, http commands

- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090
- Prometheus targets: http://localhost:9090/targets
- Example Prometheus query: [`sum by (application) (rate(http_server_requests_seconds_count[5m]))`](http://localhost:9090/graph?g0.expr=sum%20by%20%28application%29%20%28rate%28http_server_requests_seconds_count%5B5m%5D%29%29&g0.tab=0&g0.stacked=0&g0.show_exemplars=0&g0.range_input=5m)
- Example url to the Dog Client: http://localhost:8081/owner/scott/dogs
- Example command to the Dog Client: `http :8081/owner/scott/dogs`
- Example url to the Dog Service: http://localhost:8080/dogs?aregood=true
- Example command to the Dog Service: `http ':8080/dogs?aregood=true'`
- Actuator Endpoint: http://localhost:8081/actuator
- Info Endpoint: http://localhost:8081/actuator/info
- Metrics Endpoint: http://localhost:8081/actuator/metrics
- Querying one metric: http://localhost:8081/actuator/metrics/http.server.requests
- Querying tags: http://localhost:8081/actuator/metrics/http.server.requests?tag=status:404
- Prometheus Endpoint: http://localhost:8081/actuator/prometheus
- Requesting OpenMetrics format: `http :8081/actuator/prometheus 'Accept: application/openmetrics-text; version=1.0.0'`



## Troubleshooting

### Maven

If you get an error that indicates a corrupt jar file (empty zip, invalid jar, etc.), e.g.:

```
java.io.IOException: Error reading file .../.m2/repository/.../something-1.2.3.jar: zip file is empty
```

You should navigate to your maven local cache and delete these files or containing folder. On Linux/Mac it should be under `"$HOME/.m2/repository"` on Windows its `%HOMEDRIVE%%HOMEPATH%\.m2\repository` (Do not remove the whole `.m2` or `.m2/repository` folder!). After removing the files, you can run `./mvnw package` again.



### Docker

If you have issues with Docker containers, you can remove them by executing

```shell
docker compose down
```

(Tempo will need ~10s to shut down.)

If you still have issues, you can try deleting the volumes too:

```shell
docker compose down --volumes
```



### IntelliJ IDEA

If IntelliJ has issues importing your project, completely close it, delete the `.idea` folder and the `*.iml` files, run `./mvnw package` and try to import the project again.



### Tempo

If everything seemingly works in Grafana but it seems it is not able to connect to Tempo, it might be because IntelliJ IDEA (I think it opens the same port as Tempo wants - Jonatan):

1. Completely close IntelliJ
1. Restart Tempo
1. Check if Tempo works
1. Start IntelliJ again
