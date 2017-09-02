package com.aws.examples.s3;

import com.amazonaws.regions.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import com.aws.examples.config.MyAWSConfig;

import java.io.File;
import java.io.IOException;

/**
 * Created by krishna1bhat on 9/2/17.
 */

//Amazon S3 stored files as an Object not BLOB based(storing database, OS...)
//Files are stored in buckets(fancy name of folder)

public class UploadObjectSingleOperation {
    private AmazonS3 s3client = new AmazonS3Client(MyAWSConfig.awsCredentials);

    public static void main(String... args) throws IOException{
        UploadObjectSingleOperation s3client = new UploadObjectSingleOperation();
        s3client.uploadFile();
    }

    private void uploadFile(){
        s3client.setRegion(com.amazonaws.regions.Region.getRegion(Regions.US_WEST_1));

        String existingBucketName  = MyAWSConfig.testBucketName;
        String keyName             = "file name";
        String filePath            = "/Users/krishna1bhat/Desktop/test.txt";

        S3ClientOptions a = new S3ClientOptions();
        a.setPathStyleAccess(true);
        s3client.setS3ClientOptions(a);

        try{
            System.out.println("Uploading a file...");
            File file = new File(filePath);
            long startTime = System.currentTimeMillis();
            s3client.putObject(new PutObjectRequest(existingBucketName, keyName, file));
            long endTime = System.currentTimeMillis();
            System.out.println("File uploaded successfully: " + (endTime-startTime));
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
