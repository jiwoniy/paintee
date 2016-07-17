package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class User extends BaseEntity {
    private String userId;

    private String password;

    private String email;

    private String name;

    private String providerId;

    private String introduce;

    private String basicAddr;

    private String detailAddr;

    private String zipcode;

    private String city;

    private String location;

    private Integer uploadCnt;

    private Integer postCnt;

    private Float earnTotalMoney;

    private Float earnRewordMoney;

    private String resentSendBasicAddr;

    private String resentSendDetailAddr;

    private String resentSendZipcode;

    private String resentSendCity;

    private String resentSendLocation;

    private String resentSendName;

    private Integer point;

    private String userStatus;

    private String snsType;

    private Date createdDate;

    private String language;

    private Integer serviceCnt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBasicAddr() {
        return basicAddr;
    }

    public void setBasicAddr(String basicAddr) {
        this.basicAddr = basicAddr;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getUploadCnt() {
        return uploadCnt;
    }

    public void setUploadCnt(Integer uploadCnt) {
        this.uploadCnt = uploadCnt;
    }

    public Integer getPostCnt() {
        return postCnt;
    }

    public void setPostCnt(Integer postCnt) {
        this.postCnt = postCnt;
    }

    public Float getEarnTotalMoney() {
        return earnTotalMoney;
    }

    public void setEarnTotalMoney(Float earnTotalMoney) {
        this.earnTotalMoney = earnTotalMoney;
    }

    public Float getEarnRewordMoney() {
        return earnRewordMoney;
    }

    public void setEarnRewordMoney(Float earnRewordMoney) {
        this.earnRewordMoney = earnRewordMoney;
    }

    public String getResentSendBasicAddr() {
        return resentSendBasicAddr;
    }

    public void setResentSendBasicAddr(String resentSendBasicAddr) {
        this.resentSendBasicAddr = resentSendBasicAddr;
    }

    public String getResentSendDetailAddr() {
        return resentSendDetailAddr;
    }

    public void setResentSendDetailAddr(String resentSendDetailAddr) {
        this.resentSendDetailAddr = resentSendDetailAddr;
    }

    public String getResentSendZipcode() {
        return resentSendZipcode;
    }

    public void setResentSendZipcode(String resentSendZipcode) {
        this.resentSendZipcode = resentSendZipcode;
    }

    public String getResentSendCity() {
        return resentSendCity;
    }

    public void setResentSendCity(String resentSendCity) {
        this.resentSendCity = resentSendCity;
    }

    public String getResentSendLocation() {
        return resentSendLocation;
    }

    public void setResentSendLocation(String resentSendLocation) {
        this.resentSendLocation = resentSendLocation;
    }

    public String getResentSendName() {
        return resentSendName;
    }

    public void setResentSendName(String resentSendName) {
        this.resentSendName = resentSendName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getSnsType() {
        return snsType;
    }

    public void setSnsType(String snsType) {
        this.snsType = snsType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getServiceCnt() {
        return serviceCnt;
    }

    public void setServiceCnt(Integer serviceCnt) {
        this.serviceCnt = serviceCnt;
    }
}