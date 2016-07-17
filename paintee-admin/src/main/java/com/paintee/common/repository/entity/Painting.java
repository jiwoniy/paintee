package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class Painting extends BaseEntity {
    private Integer seq;

    private String paintingId;

    private Date uploadDate;

    private String artistId;

    private String sentence;

    private String location;

    private Integer postedNum;

    private Integer postedPeopleCnt;

    private String originalSize;

    private Integer viewCnt;

    private Integer shareCnt;

    private Long fileGroupSeq;

    private String paintingStatus;

    private String privateAt;

    private Date createdDate;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(String paintingId) {
        this.paintingId = paintingId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPostedNum() {
        return postedNum;
    }

    public void setPostedNum(Integer postedNum) {
        this.postedNum = postedNum;
    }

    public Integer getPostedPeopleCnt() {
        return postedPeopleCnt;
    }

    public void setPostedPeopleCnt(Integer postedPeopleCnt) {
        this.postedPeopleCnt = postedPeopleCnt;
    }

    public String getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(String originalSize) {
        this.originalSize = originalSize;
    }

    public Integer getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Integer getShareCnt() {
        return shareCnt;
    }

    public void setShareCnt(Integer shareCnt) {
        this.shareCnt = shareCnt;
    }

    public Long getFileGroupSeq() {
        return fileGroupSeq;
    }

    public void setFileGroupSeq(Long fileGroupSeq) {
        this.fileGroupSeq = fileGroupSeq;
    }

    public String getPaintingStatus() {
        return paintingStatus;
    }

    public void setPaintingStatus(String paintingStatus) {
        this.paintingStatus = paintingStatus;
    }

    public String getPrivateAt() {
        return privateAt;
    }

    public void setPrivateAt(String privateAt) {
        this.privateAt = privateAt;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}