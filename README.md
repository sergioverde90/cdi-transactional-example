# Example with Docker, CDI and @Transactional annotation
Complete example with a CDI and transactional resource.

You can test this example as follow:
* Build war file
```bash
mvn clean install
```
* Build the image
```bash
docker build -t wildfly-cdi .
```
* Run a container based in this image
```bash
docker run -d --name cdi -p 8080:8080 wildfly-cdi
```
* test the example
```bash
curl -XPUT -H "Content-Type: application/json" -d '{"id":1,"name":"name1"}' http://localhost:8080/cdi-transactional/resources/cdi/tx
```
