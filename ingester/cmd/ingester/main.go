package main

import (
	"bytes"
	"fmt"
	"log"
	"mime/multipart"
	"net/http"
	"os"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
	"github.com/gin-gonic/gin"

	"ingester/pkg/s3uploader"
)

type PDFUploadRequest struct {
	File *multipart.FileHeader `form:"file" binding:"required"`
}

func uploadFileToS3(fileBytes []byte, fileName string) error {
	sess, err := session.NewSession(&aws.Config{
		Region: aws.String("us-east-1"),
	})

	if err != nil {
		return err
	}

	s3Client := s3.New(sess)

	_, err = s3Client.PutObject(&s3.PutObjectInput{
		Bucket: aws.String("your-s3-bucket-name"),
		Key:    aws.String(fileName),
		Body:   bytes.NewReader(fileBytes),
	})

	return err
}

func main() {
	r := gin.Default()
	uploader := &s3uploader.RealS3Uploader{}

	r.Use(gin.Logger()) // Enhanced logging

	r.GET("/health", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{"status": "UP"})
	})

	r.POST("/upload", func(c *gin.Context) {
		var request PDFUploadRequest
		if err := c.ShouldBind(&request); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		file := request.File

		if file.Size == 0 {
			c.JSON(http.StatusBadRequest, gin.H{"error": "File is empty"})
			return
		}

		openedFile, err := file.Open()
		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Cannot open file: %s", err.Error())})
			return
		}
		defer openedFile.Close()

		fileBytes := make([]byte, file.Size)
		_, err = openedFile.Read(fileBytes)
		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Cannot read file: %s", err.Error())})
			return
		}

		err := uploader.UploadFile(fileBytes, file.Filename)
		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Upload to S3 failed: %s", err.Error())})
			return
		}

		c.JSON(http.StatusOK, gin.H{"message": fmt.Sprintf("File %s uploaded successfully", file.Filename)})
	})

	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
		log.Printf("Defaulting to port %s", port)
	}

	r.Run(":" + port)
}
