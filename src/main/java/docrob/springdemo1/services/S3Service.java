package docrob.springdemo1.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.time.Instant;

// from https://www.youtube.com/watch?v=vY7c7k8xmKE
// and https://docs.aws.amazon.com/code-samples/latest/catalog/java-s3-src-main-java-aws-example-s3-GeneratePresignedURL.java.html

@Service
@Slf4j
public class S3Service {
    @Value("${aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 s3Client;

//    public S3Service(AmazonS3 s3Client) {
//
//    }

    public byte[] downloadFile(String fileName) {
        S3Object s3o = s3Client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3o.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucket, fileName);
        return fileName + " deleted";
    }

    public String uploadFile(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream input = null;
            input = new FileInputStream(file);
            byte [] bytes = IOUtils.toByteArray(input);
            CustomMultipartFile mpFile = new CustomMultipartFile(bytes);
            String [] nameParts = fileName.split("/");
            mpFile.setName(nameParts[nameParts.length - 1]);
            mpFile.setOriginalName(fileName);
            mpFile.determineContentType(nameParts[nameParts.length - 1]);
            System.out.println(mpFile);
            return uploadFile(mpFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadFile(MultipartFile file) {
        File convertedFile = convertMultipartFileToFile(file);
        // generate a unique-ish s3 filename based on file's name + current time
        String fileName = file.getOriginalFilename() + System.currentTimeMillis();
        s3Client.putObject(bucket, fileName, convertedFile);
        convertedFile.delete();
        // return the file's s3 name since you may need to store it somewhere
        return fileName;
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return convertedFile;
    }

    public String getSignedURL(String fileName) {
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 5; // default to 5 minute expiration
        expiration.setTime(expTimeMillis);

        // Generate the presigned URL.
        log.info("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
