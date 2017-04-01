## avasec simple java backend

### Installation

1. Get MongoDB from [Docker repository](https://hub.docker.com/_/mongo)

`docker pull mongo`

2. Start MongoDB (expose 27017 locally to allow access from host)

`docker run --name asdb -p 27017:27017 -d mongo`

3. Build

`./gradlew build buildDocker`

4. Build the image

`docker build -t asback2 build/docker`

5. Start backend

`docker --name asback2 run -e "spring.data.mongodb.uri=mongodb://asdb:27017/test" -p 8080:8080 --link asdb:mongo -t asback2`

### Test

[http://localhost:8080/role](http://localhost:8080/role)


