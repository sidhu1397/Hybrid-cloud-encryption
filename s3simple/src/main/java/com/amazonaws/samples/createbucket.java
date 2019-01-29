package com.amazonaws.samples;
import java.io.File;
import java.util.UUID;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class createbucket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 AWSCredentials credentials = null;
	        try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location (C:\\Users\\sidhu\\.aws\\credentials), and is in valid format.",
	                    e);
	        }

	        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
	            .withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .withRegion("us-east-2")
	            .build();
	        JFrame f=new JFrame("create bucket");
	        JTextField f1=new JTextField();
	        f1.setBounds(50,50, 150,20);  
	        JButton b1=new JButton("create bucket");
	        b1.setBounds(50,100, 95,30);  
	        JLabel l=new JLabel("Bucket name");
	         b1.addActionListener(new ActionListener() {
	        	 public void actionPerformed(ActionEvent e)
	        	 {
	       try {
	    	   
	    	   
	    	   String bname=f1.getText();
	    	   System.out.println(bname);
	    	   
	        System.out.println("creating bucket");
	       s3.createBucket(bname);
	       JOptionPane.showMessageDialog(f,"bucket successfully created");
	      
	       } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which means your request made it "
	                    + "to Amazon S3, but was rejected with an error response for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means the client encountered "
	                    + "a serious internal problem while trying to communicate with S3, "
	                    + "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }

	}
	        });
	         f.add(l);
		        f.add(f1);
		        f.add(b1);
		        f.setSize(400, 400);
		       f.setLayout(null);
		       f.setVisible(true);

}
	}
