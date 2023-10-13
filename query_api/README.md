# Query API
This service provides an API for web and mobile apps to ask questions, we will also provide history access via this API (or break it into it's own service in the future).

Design is TBD, we need to decide if this can just be a python api serving access to the LLM or maybe a HA API service that dumps query requests into a message queue for processing. Probably the latter because of the legnth of time needed to service LLM requests under load. I should make an ADR to discuss this...