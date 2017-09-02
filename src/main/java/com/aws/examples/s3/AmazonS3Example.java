package com.aws.examples.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.aws.examples.config.MyAWSConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by krishna1bhat on 9/2/17.
 */
public class AmazonS3Example {
    // create a client connection based on credentials
    private static AmazonS3 s3client = new AmazonS3Client(MyAWSConfig.awsCredentials);

    private static final String SUFFIX = "/";

    public static void main(String... args){

        // create bucket - name must be unique for all S3 users
        String bucketName = "test-example-bucket-again";
        s3client.createBucket(bucketName);

        // list buckets
        for (Bucket bucket : s3client.listBuckets()) {
            System.out.println(" - " + bucket.getName());
        }

        // create folder into bucket
        String folderName = "testfolder";
        createFolder(bucketName, folderName);

        // upload file to folder and set it to public
        String fileName = folderName + SUFFIX + "test.txt";
        s3client.putObject(new PutObjectRequest(bucketName, fileName,
                new File("/Users/krishna1bhat/Desktop/test.txt"))
                .withCannedAcl(CannedAccessControlList.PublicRead));

        //delete folder inside bucket
        deleteFolder(bucketName, folderName);

        // deletes bucket
        s3client.deleteBucket(bucketName);
    }

    public static void createFolder(String bucketName, String folderName) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                folderName + SUFFIX, emptyContent, metadata);
        // send request to S3 to create folder
        s3client.putObject(putObjectRequest);
    }
    /**
     * This method first deletes all the files in given folder and than the
     * folder itself
     */
    public static void deleteFolder(String bucketName, String folderName) {
        List<S3ObjectSummary> fileList = s3client.listObjects(bucketName, folderName).getObjectSummaries();
        for (S3ObjectSummary file : fileList) {
            s3client.deleteObject(bucketName, file.getKey());
        }
        s3client.deleteObject(bucketName, folderName);
    }
}
