## avasec simple java backend

### Installation

1. Get MongoDB from [Docker repository](https://hub.docker.com/_/mongo)

`docker pull mongo`

2. Start MongoDB (expose 27017 locally to allow access from host)

`docker run --name asdb -p 27017:27017 -d mongo`

3. Get backend from [Docker repository](https://hub.docker.com/r/av4sec/asback2)

`docker pull av4sec/asback2`

4. Start backend

`docker run --name asback2 -e "spring.data.mongodb.uri=mongodb://asdb:27017/test" -p 8080:8080 --link asdb:mongo -t av4sec/asback2`

### Update backend to latest version

1. Stop MongoDB and backend

`docker stop asback2 asdb`

2. Refresh backend from [Docker repository](https://hub.docker.com/r/av4sec/asback2)

`docker pull av4sec/asback2:latest`

3. Restart MongoDB and backend

`docker start asdb asback2`

4. (Optional) For debugging you can attach your current terminal to the backend

`docker attach asback2`

### Test

* [http://localhost:8080/api/role](http://localhost:8080/api/role)
* [http://localhost:8080/api/role/10003](http://localhost:8080/api/role/10003)

* `curl -F "file=@/Users/.../role_01.csv" http://localhost:8080/api/upload/file/role`

* [http://localhost:8080/api/acode](http://localhost:8080/api/acode)
* [http://localhost:8080/api/acode/10003](http://localhost:8080/api/acode/10003)

* `curl -F "file=@/Users/.../acode_01.csv" http://localhost:8080/api/upload/file/acode`


### DIY

* `./gradlew build buildDocker`
* `docker build -t asback2 build/docker`
