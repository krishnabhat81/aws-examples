package com.aws.examples.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * Created by krishna1bhat on 9/2/17.
 */
public class MyAWSConfig {
    //My IAM user - User1 in Group1 with all privileges.
    //get those key from amazon aws account...
    public static AWSCredentials awsCredentials = new BasicAWSCredentials(
            "YOUR_ACCESS_KEY",
            "YOUR_SECRET_KEY"
    );

    public static String testBucketName = "krishna-456";//testing - bucket name must be universally unique
}
