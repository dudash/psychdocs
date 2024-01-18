# Query API
This service provides an API for web and mobile apps to ask questions, we will also provide history access via this API (or break it into it's own service in the future).

Design is TBD, we need to decide if this can just be a python api serving access to the LLM or maybe a HA API service that dumps query requests into a message queue for processing. Probably the latter because of the legnth of time needed to service LLM requests under load. I should make an ADR to discuss this...


## Developer's Notes
If you are using podman you will need to do the following to allow test containers to work:
* edit `~/.testcontainers.properties` to include `ryuk.container.privileged=true`
* `podman machine set --rootful`

### Running locally
With `quarkus dev` we will run unit tests and start a server on localhost:5000. This is configured via `application.properties`.

###