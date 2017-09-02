package com.aws.examples.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.aws.examples.config.MyAWSConfig;

/**
 * Created by krishna1bhat on 9/2/17.
 */
public class CreateBucket {
    private AmazonS3 s3client = new AmazonS3Client(MyAWSConfig.awsCredentials);
    private static String bucketName     = MyAWSConfig.testBucketName;//bucket name must be universally unique

    public static void main(String... args){
        new CreateBucket();
    }

    CreateBucket(){
        s3client.setRegion(Region.getRegion(Regions.US_WEST_1));

        try {
            if(!(s3client.doesBucketExist(bucketName)))
            {
                // Note that CreateBucketRequest does not specify region. So bucket is
                // created in the region specified in the client.
                s3client.createBucket(new CreateBucketRequest(bucketName));
            }
            // Get location.
            String bucketLocation = s3client.getBucketLocation(new GetBucketLocationRequest(bucketName));
            System.out.println("bucket location = " + bucketLocation);

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}
