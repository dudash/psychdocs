# Ingester Service
This will take the PDFs from the webapp and dump them into S3 for us. The event driven system flow will trigger updates to rebuild the vector embedings and make them available to the backend services that use them.

## Running tips
### Locally
Make sure to set env vars needed to your storage:
```
export AWS_REGION=us-east-1
export S3_BUCKET_NAME=my-s3-bucket
```

### K8s / OpenShift
TBD