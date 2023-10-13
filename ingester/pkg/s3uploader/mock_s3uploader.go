package s3uploader

type MockS3Uploader struct {
	Err error
}

func (u *MockS3Uploader) UploadFile(fileBytes []byte, fileName string) error {
	if u.Err != nil {
		return u.Err
	}
	return nil
}
