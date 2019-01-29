package com.amazonaws.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class uploadfileaes {

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
        JFrame f=new JFrame("upload file");
        JButton b1=new JButton("upload");
        JButton b2=new JButton("select file");
      //  JTextField f1=new JTextField();
        JTextField f2=new JTextField();
        JTextField f3=new JTextField();
       // f1.setBounds(100,50, 150,20);
        f2.setBounds(100,150, 150,20);
        f3.setBounds(100,250, 150,20);
        b2.setBounds(250,250,95,30);
        b1.setBounds(100,350, 95,30);
     //   JLabel l1=new JLabel("bucket name");
        JLabel l2=new JLabel("file key");
        JLabel l3=new JLabel("file path");
       // l1.setBounds(30, 50, 150, 20);
        l2.setBounds(30,150, 150, 20);
        l3.setBounds(30,250, 150, 20);
        JFileChooser fc=new JFileChooser();
      
        
        
        b2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		
        		int i=fc.showOpenDialog(f);
        		if(i==JFileChooser.APPROVE_OPTION)
        		{
        			File file=fc.getSelectedFile();
        			String fpath=file.getAbsolutePath();
        			f3.setText(fpath);
        			
        		}
        		
        	}
        });
        b1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	
        	{
        try
        {   String borg="yoyooriginal";
        	System.out.println("uploading file");
        	String bname="yoyokeepituptv";
        	String bname2="yoyokeepituptv2";
        	String bname3="yoyokeepituptv3";
        	String key1=f2.getText();
        	String filepath=f3.getText();
        	System.out.println("converting file to sstring");
        	BufferedReader reader=new BufferedReader(new FileReader(filepath));
        	StringBuilder complete=new StringBuilder();
        	String line = null;
        	String ls = System.getProperty("line.separator");
        	while((line=reader.readLine())!=null)
        	{
        		complete.append(line);
        		complete.append(ls);
        	}
        	complete.deleteCharAt(complete.length()-1);
        	reader.close();
        	String filecontent=complete.toString();
        	System.out.println(filecontent);
        	int n=filecontent.length();
        	int csize=n/3;
        	int count=0;
        	String filename1=randomstr();
        	String filename2=randomstr();
        	String filename3=randomstr();
        	String orgfile=key1;
        	System.out.println(filename1);
        	System.out.println(filename2);
        	System.out.println(filename3);
        	File file1=new File(filename1+".txt");
        	File file2=new File(filename2+".txt");
        	File file3=new File(filename3+".txt");
        	File ofile=new File(orgfile+".txt");
        	FileWriter fw1=new FileWriter(file1);
    		FileWriter fw2=new FileWriter(file2);
    		FileWriter fw3=new FileWriter(file3);
    		PrintStream ofw=new PrintStream(ofile);
    		StringBuilder fsb1=new StringBuilder();
    		StringBuilder fsb2=new StringBuilder();
    		StringBuilder fsb3=new StringBuilder();
        	
        	
        	for(int i=0;i<n;i++)
        	{
        		count++;
        		
        		if(count>0&&count<=(1*csize))
        		{
        			char c=filecontent.charAt(i);
        			fsb1.append(c);
        			
        		}
        		if(count>(1*csize)&&count<=(2*csize))
        		{
        			char c=filecontent.charAt(i);
        			fsb2.append(c);
        		}
        		if(count>(2*csize)&&count<=(3*csize))
        		{
        			char c=filecontent.charAt(i);
        			fsb3.append(c);
        		}
        		
        	}
        	String fs1=fsb1.toString();
        	String fs2=fsb2.toString();
        	String fs3=fsb3.toString();
        	System.out.println(fs1);
        	System.out.println(fs2);
        	System.out.println(fs3);
        	List<Object> edetails1=aesfunc(fs1);
        	List<Object> edetails2=aesfunc(fs1);
        	List<Object> edetails3=aesfunc(fs1);
        	System.out.println("File 1 Secretkey= "+edetails1.get(0).getClass()+" IV= "+edetails1.get(1).getClass()+" Ciphertext= "+edetails1.get(2));      
        	System.out.println("File 2 Secretkey= "+edetails2.get(0).getClass()+" IV= "+edetails2.get(1).getClass()+" Ciphertext= "+edetails2.get(2)); 
        	System.out.println("File 3 Secretkey="+edetails3.get(0)+"IV"+edetails3.get(1)+"Ciphertext"+edetails3.get(2)); 
        	fw1.write(edetails1.get(2).toString());
        	fw1.close();
        	fw2.write(edetails2.get(2).toString());
        	fw2.close();
        	fw3.write (edetails3.get(2).toString());
        	fw3.close();
        	
        	ofw.println(filename1+","+bname+","+Base64.getEncoder().encodeToString(((Key) edetails1.get(0)).getEncoded())+","+Base64.getEncoder().encodeToString((byte[]) edetails1.get(1)));
        	
        	
        	ofw.println(filename2+","+bname2+","+Base64.getEncoder().encodeToString(((Key) edetails2.get(0)).getEncoded())+","+Base64.getEncoder().encodeToString((byte[]) edetails2.get(1)));
        
        	ofw.println(filename3+","+bname3+","+Base64.getEncoder().encodeToString(((Key) edetails3.get(0)).getEncoded())+","+Base64.getEncoder().encodeToString((byte[]) edetails3.get(1)));
        	
        	ofw.close();
        	
        	System.out.println(file1.getAbsolutePath());
        	System.out.println(ofile.getAbsolutePath());
        	
        	//s3.putObject(new PutObjectRequest(bname,key1,new File(filepath)));
        	s3.putObject(new PutObjectRequest(bname,filename1,file1.getAbsoluteFile()));
        	s3.putObject(new PutObjectRequest(bname2,filename2,file2.getAbsoluteFile()));
        	s3.putObject(new PutObjectRequest(bname3,filename3,file3.getAbsoluteFile()));
        	s3.putObject(new PutObjectRequest(borg,key1,ofile.getAbsoluteFile()));
        	JOptionPane.showMessageDialog(f,"file uploaded successfully");
        	
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
            System.out.println("Error Message: " + ace.getMessage());
        } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
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
      
        f.add(l2);f.add(l3);f.add(f2);f.add(f3);f.add(b1);f.add(b2);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
        
}
  private static String randomstr()
  {
      int leftLimit = 97; // letter 'a'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = 5;
      Random random = new Random();
      StringBuilder buffer = new StringBuilder(targetStringLength);
      for (int i = 0; i < targetStringLength; i++) {
          int randomLimitedInt = leftLimit + (int) 
            (random.nextFloat() * (rightLimit - leftLimit + 1));
          buffer.append((char) randomLimitedInt);
      }
      String generatedString = buffer.toString();
   
      return generatedString;
  }
  
  public static List<Object> aesfunc(String pt) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
  {
	//AES Starts
      
		KeyGenerator keygenerator=KeyGenerator.getInstance("AES");
		keygenerator.init(128);
		SecretKey secretkey=keygenerator.generateKey();
		byte[] iv=new byte[16];
		SecureRandom random=new SecureRandom();
		random.nextBytes(iv);
		String plaintext=pt;
		byte[] ct=encrypt(plaintext.getBytes(),secretkey,iv);
		String ect=Base64.getEncoder().encodeToString(ct);
		System.out.println("Encrypted Text : "+ect );
		return Arrays.asList(secretkey,iv,ect);
		
	
  //AES ENDS
  }
	public static byte[] encrypt(byte[] pt,SecretKey sk,byte[] iv) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		
		
			Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
		
			SecretKeySpec sks=new SecretKeySpec(sk.getEncoded(),"AES");
			IvParameterSpec ivs=new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE,sks,ivs);
			 byte[] cipherText = cipher.doFinal(pt);
		        
		        
		
		
		return cipherText;
	}

	}


