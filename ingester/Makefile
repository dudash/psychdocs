build:
	go build -o ingester .

run: build
	./ingester

docker-build:
	docker build -t ingester .

docker-run: docker-build
	docker run -p 8080:8080 ingester