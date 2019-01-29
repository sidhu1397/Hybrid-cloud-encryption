package com.amazonaws.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class downloadfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AWSCredentials credentials = null;
		
		String bname="yoyooriginal";
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
        JFrame f=new JFrame("download");
        JButton b=new JButton("download");
        b.setBounds(100,350, 95,30);
        JTextField tf=new JTextField();
        tf.setBounds(100,50, 150,20);
        JLabel l1=new JLabel("filename");
        l1.setBounds(30, 50, 150, 20);
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		try
        		{
        			int count=0;
        			String line1=new String();
        			String line2=new String();
        			String line3=new String();
        			String fname=tf.getText();
        			//file 1 variables
        			String fn1=new String();
        			String bn1=new String();
        			String sk1=new String();
        			byte[] decodedkey1=new byte[16];
        			byte[] iv1=new byte[16];
        			
        			//file 2 variables
        			String fn2=new String();
        			String bn2=new String();
        			String sk2=new String();
        			byte[] decodedkey2=new byte[16];
        			byte[] iv2=new byte[16];
        			
        			//file 3 variables
        			String fn3=new String();
        			String bn3=new String();
        			String sk3=new String();
        			byte[] decodedkey3=new byte[16];
        			byte[] iv3=new byte[16];
        			
        			 System.out.println("Downloading an object");
        	            S3Object object = s3.getObject(new GetObjectRequest(bname,fname));
        	            /*System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
        	            displayTextInputStream(object.getObjectContent());*/
        	            S3ObjectInputStream s3is=object.getObjectContent();
        	            File ofile=new File(fname+".txt");
        	            Scanner sc=new Scanner(s3is);
        	            StringBuilder map=new StringBuilder();
        	            String ls = System.getProperty("line.separator");
        	            while(sc.hasNextLine())
        	            {
        	            	count++;
        	            	String temp=sc.nextLine();
        	            	map.append(temp);
        	            	if(count==1)
        	            		line1=temp;
        	            	if(count==2)
        	            		line2=temp;
        	            	if(count==3)
        	            		line3=temp;
        	            	map.append(ls);
        	            }
        	            map.deleteCharAt(map.length()-1);
        	            String mapping=map.toString();
        	            System.out.println("map"+mapping);
        	            sc.close();
        	            System.out.println(line1);
        	            System.out.println(line2);
        	            System.out.println(line3);
        	          
        	            Scanner sep1=new Scanner(line1);
        	            sep1.useDelimiter(",");
        	            while(sep1.hasNext())
        	            { 
        	            	fn1=sep1.next();
        	              bn1=sep1.next();
        	              sk1=sep1.next();
        	              decodedkey1=Base64.getDecoder().decode(sk1);
        	               
                          iv1=Base64.getDecoder().decode(sep1.next());
        	              }
        	            System.out.println(fn1+""+bn1);
        	            sep1.close();
        	            Scanner sep2=new Scanner(line2);
        	            sep2.useDelimiter(",");
        	            while(sep2.hasNext())
        	            {
        	             fn2=sep2.next();
        	             bn2=sep2.next();
        	             sk2=sep2.next();
       	              decodedkey2=Base64.getDecoder().decode(sk2);
       	              
                         iv2=Base64.getDecoder().decode(sep2.next());
        	            }
        	            System.out.println(fn2+""+bn2);
        	            sep2.close();
        	            Scanner sep3=new Scanner(line3);
        	            sep3.useDelimiter(",");
        	            while(sep3.hasNext())
        	            {
        	             fn3=sep3.next();
        	             bn3=sep3.next();
        	             sk3=sep3.next();
          	              decodedkey3=Base64.getDecoder().decode(sk3);
          	              
                            iv3=Base64.getDecoder().decode(sep3.next());
        	            }
        	            System.out.println(fn3+""+bn3);
        	            sep3.close();
        	         SecretKey   originalKey1 = new SecretKeySpec(decodedkey1, 0, decodedkey1.length, "AES");
        	         SecretKey     originalKey2 = new SecretKeySpec(decodedkey2, 0, decodedkey2.length, "AES");
        	         SecretKey     originalKey3 = new SecretKeySpec(decodedkey3, 0, decodedkey3.length, "AES"); 
        	           
        	          object=s3.getObject(new GetObjectRequest(bn1,fn1));
        	          BufferedReader br1=new BufferedReader(new InputStreamReader(object.getObjectContent()));
        	          File opfile=new File("D:s3outputfiles/"+fname+".txt");
        	          Writer writer = new OutputStreamWriter(new FileOutputStream(opfile));
        	          while (true) {          
        	        	     String line = br1.readLine();           
        	        	     if (line == null)
        	        	          break;            
                              String decryptedtext1=decrypt(Base64.getDecoder().decode(line),originalKey1,iv1);
        	        	     writer.write(decryptedtext1);
        	        	}
        	          System.out.println("line 143");
        	          object=s3.getObject(new GetObjectRequest(bn2,fn2));
        	          BufferedReader br2=new BufferedReader(new InputStreamReader(object.getObjectContent()));
        	          while (true) {          
     	        	     String line = br2.readLine();           
     	        	     if (line == null)
     	        	          break;     
     	        	    String decryptedtext1=decrypt(Base64.getDecoder().decode(line),originalKey2,iv2);
   	        	     writer.write(decryptedtext1);

     	        	     
     	        	} System.out.println("line 152");
     	        	object=s3.getObject(new GetObjectRequest(bn3,fn3));
     	        	 BufferedReader br3=new BufferedReader(new InputStreamReader(object.getObjectContent()));
     	        	 while (true) {          
    	        	     String line = br3.readLine();           
    	        	     if (line == null)
    	        	          break;     
    	        	     String decryptedtext1=decrypt(Base64.getDecoder().decode(line),originalKey3,iv3);
    	        	     writer.write(decryptedtext1);

    	        	     
    	        	}
     	        	

        	        	writer.close();
        	            JOptionPane.showMessageDialog(f,"file downloaded successfully check your output directory");
        	            
        		}catch (AmazonServiceException ase) {
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
    	            System.out.println("Error Message: " + ace.getMessage());} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidAlgorithmParameterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        
        });
        f.add(tf);f.add(b);f.add(l1);
        f.setSize(400, 400);
        f.setLayout(null);
        
        f.setVisible(true);
        

	}
	public static String decrypt(byte[] ct,SecretKey sk,byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec sks=new SecretKeySpec(sk.getEncoded(),"AES");
		IvParameterSpec ivs=new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE,sks,ivs);
		byte[] dt=cipher.doFinal(ct);
		return new String(dt);
		
	}

}
