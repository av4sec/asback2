## avasec simple java backend

### Installation

1. Get MongoDB from [Docker repository](https://hub.docker.com/_/mongo)

`docker pull mongo`

2. Start MongoDB (expose 27017 locally to allow access from host)

`docker run --name asdb -p 27017:27017 -d mongo`

3. Get backend from [Docker repository](https://hub.docker.com/r/av4sec/asback2)

`docker pull av4sec/asback2`

4. Start backend

`docker run -e "spring.data.mongodb.uri=mongodb://asdb:27017/test" -p 8080:8080 --link asdb:mongo -t av4sec/asback2`


### Test

* [http://localhost:8080/api/role](http://localhost:8080/api/role)
* [http://localhost:8080/api/role/10003](http://localhost:8080/api/role/10003)


### DIY

* `./gradlew build buildDocker`
* `docker build -t asback2 build/docker`
