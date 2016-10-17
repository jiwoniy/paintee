/**
@file AWSS3Helper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | AWSS3Helper.java |    
| Package | com.paintee.common.aws.s3 |    
| Project name | paintee-admin |    
| Type name | AWSS3Helper |    
| Company | Paintee | 
| Create Date | 2016 2016. 4. 1. 오후 12:27:58 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.aws.s3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

/**
@class AWSS3Helper
com.paintee.common.aws.s3 \n
   ㄴ AWSS3Helper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 4. 1. 오후 12:27:58 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - aws s3 용 helper
 s3 사용을 위한 policy 지정해줘야함.
*/
@Component(value="com.paintee.common.aws.s3.AWSS3Helper")
public class AWSS3Helper {
	private final static Logger logger = LoggerFactory.getLogger(AWSS3Helper.class);

    @Autowired
    private AmazonS3Client client;

    /**
     * @param bucketName 버킷 명
     * @param s3Path     업로드할 경로
     * @param file       해당 파일 객체
     * @fn putObject
     * @brief 함수 간략한 설명 : s3 에 파일 업로드
     * @remark - 함수의 상세 설명 :
     */
    public void putObject(String bucketName, String s3Path, File file) {
    	//TODO:iam 권한부여가 안되고 있어 버킷 생성은 미리 해둬야 
//        if (!client.doesBucketExist(bucketName)) {
//            client.createBucket(bucketName);
//        }
        client.putObject(new PutObjectRequest(bucketName, s3Path, file));
    }

    /**
     * @param bucketName
     * @param s3Path
     * @param inputStream
     * @param metadata
     * @fn putObject
     * @brief 함수 간략한 설명 :
     * @remark - 함수의 상세 설명 :
     */
    public void putObject(String bucketName, String s3Path, InputStream inputStream, ObjectMetadata metadata) {
    	//TODO:iam 권한부여가 안되고 있어 버킷 생성은 미리 해둬야 
//        if (!client.doesBucketExist(bucketName)) {
//            client.createBucket(bucketName);
//        }

        PutObjectResult putObjectResult = client.putObject(new PutObjectRequest(bucketName, s3Path, inputStream, metadata));

        logger.debug("putObjectResult:{}", putObjectResult);

        IOUtils.closeQuietly(inputStream, null);
    }

    /**
     * @param bucketName
     * @param s3Path
     * @fn deleteObject
     * @brief 함수 간략한 설명 :
     * @remark - 함수의 상세 설명 :
     */
    public void deleteObject(String bucketName, String s3Path) {
        client.deleteObject(new DeleteObjectRequest(bucketName, s3Path));
    }

    /**
     * @param bucketName 버킷 명
     * @param prefix
     * @return
     * @fn getObjectList
     * @brief 함수 간략한 설명 : 특정 버킷의 파일 목록
     * @remark - 함수의 상세 설명 :
     */
    public List<S3ObjectSummary> getObjectList(String bucketName, String prefix) {
        ObjectListing objectListing = client.listObjects(bucketName, prefix);
        return objectListing.getObjectSummaries();
    }

    /**
     * @param bucketName 버킷 명
     * @fn deleteBucket
     * @brief 함수 간략한 설명 : 특정 버킷 삭제
     * @remark - 함수의 상세 설명 :
     */
    public void deleteBucket(String bucketName) {
        client.deleteBucket(bucketName);
    }

    /**
     * @param bucketName
     * @param s3Path
     * @return 특정 경로의 파일 byte
     * @throws IOException
     * @fn download
     * @brief 함수 간략한 설명 : 특정 경로의 파일을 다운로드하여 byte배열로 리턴
     * @remark - 함수의 상세 설명 :
     */
    public byte[] download(String bucketName, String s3Path) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, s3Path);

        S3Object s3Object = client.getObject(getObjectRequest);

        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

        //log.debug("client.getS3AccountOwner()={}", client.getS3AccountOwner());

        return IOUtils.toByteArray(objectInputStream);
    }
}
