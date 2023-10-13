# Use the official Golang image from the Docker Hub
FROM golang:1.18

# Copy the local package files to the container’s workspace.
ADD . /app

# Set the current working directory inside the container
WORKDIR /app

# Download all the dependencies
RUN go get -d -v ./...

# Install the package
RUN go install -v ./...

# Run the executable
CMD ["app"]
