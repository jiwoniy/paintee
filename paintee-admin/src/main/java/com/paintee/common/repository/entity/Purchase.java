package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class Purchase extends BaseEntity {
    private Integer seq;

    private String userId;

    private String paintingId;

    private Date purchaseDate;

    private String sentence;

    private String privateAt;

    private String receiverBasicAddr;

    private String receiverDetailAddr;

    private String receiverZipcode;

    private String receiverCity;

    private String receiverName;

    private String senderAddr;

    private String senderName;

    private String location;

    private String purchaseStatus;

    private Date statusUpdateDate;

    private Date createdDate;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(String paintingId) {
        this.paintingId = paintingId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getPrivateAt() {
        return privateAt;
    }

    public void setPrivateAt(String privateAt) {
        this.privateAt = privateAt;
    }

    public String getReceiverBasicAddr() {
        return receiverBasicAddr;
    }

    public void setReceiverBasicAddr(String receiverBasicAddr) {
        this.receiverBasicAddr = receiverBasicAddr;
    }

    public String getReceiverDetailAddr() {
        return receiverDetailAddr;
    }

    public void setReceiverDetailAddr(String receiverDetailAddr) {
        this.receiverDetailAddr = receiverDetailAddr;
    }

    public String getReceiverZipcode() {
        return receiverZipcode;
    }

    public void setReceiverZipcode(String receiverZipcode) {
        this.receiverZipcode = receiverZipcode;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(String senderAddr) {
        this.senderAddr = senderAddr;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Date getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(Date statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}