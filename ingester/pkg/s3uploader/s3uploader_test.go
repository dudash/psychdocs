package s3uploader

import (
	"errors"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestUploadFile_Success(t *testing.T) {
	uploader := &MockS3Uploader{}

	err := uploader.UploadFile([]byte("test content"), "testfile.pdf")

	assert.Nil(t, err)
}

func TestUploadFile_Error(t *testing.T) {
	uploader := &MockS3Uploader{
		Err: errors.New("forced error"),
	}

	err := uploader.UploadFile([]byte("test content"), "testfile.pdf")

	assert.NotNil(t, err)
	assert.Equal(t, "forced error", err.Error())
}
