package s3uploader

import (
	"bytes"
	"log"
	"os"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
)

type RealS3Uploader struct{}

func (u *RealS3Uploader) UploadFile(fileBytes []byte, fileName string) error {
	awsRegion := os.Getenv("AWS_REGION")
	if awsRegion == "" {
		awsRegion = "us-east-1" // Fallback to "us-east-1" if AWS_REGION is not set
		log.Println("AWS_REGION is not set, falling back to us-east-1")
	}

	sess, err := session.NewSession(&aws.Config{
		Region: aws.String(awsRegion),
	})

	if err != nil {
		return err
	}

	s3Client := s3.New(sess)

	s3BucketName := os.Getenv("S3_BUCKET_NAME")
	if s3BucketName == "" {
		s3BucketName = "psychdocs-shared-ingester" // Fallback to "psychdocs-shared-ingester" if S3_BUCKET_NAME is not set
		log.Println("S3_BUCKET_NAME is not set, falling back to psychdocs-shared-ingester")
	}

	_, err = s3Client.PutObject(&s3.PutObjectInput{
		Bucket: aws.String(s3BucketName),
		Key:    aws.String(fileName),
		Body:   bytes.NewReader(fileBytes),
	})

	return err
}
