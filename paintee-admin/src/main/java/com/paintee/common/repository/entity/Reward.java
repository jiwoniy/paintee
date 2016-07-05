package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class Reward extends BaseEntity {
    private Integer seq;

    private String userId;

    private String bank;

    private String directInputBank;

    private String accountNo;

    private String accountName;

    private Integer earmRequestedMoney;

    private Integer earmRequestedCommission;

    private String rewardStatus;

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDirectInputBank() {
        return directInputBank;
    }

    public void setDirectInputBank(String directInputBank) {
        this.directInputBank = directInputBank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getEarmRequestedMoney() {
        return earmRequestedMoney;
    }

    public void setEarmRequestedMoney(Integer earmRequestedMoney) {
        this.earmRequestedMoney = earmRequestedMoney;
    }

    public Integer getEarmRequestedCommission() {
        return earmRequestedCommission;
    }

    public void setEarmRequestedCommission(Integer earmRequestedCommission) {
        this.earmRequestedCommission = earmRequestedCommission;
    }

    public String getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(String rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}