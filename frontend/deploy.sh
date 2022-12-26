docker stop cepingproject
docker rm cepingproject
docker rmi ceping
docker build -t ceping .
docker run -d -p 8080:8080 --name cepingproject ceping