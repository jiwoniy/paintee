package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class PromotionCode extends BaseEntity {
	private Integer seq;
	
    private String usedUser;
    private String codeValue;
    private Date issueDate;
    private Date usedDate;
    private String usedEmail;
    private String useYn;
    
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    
    
    public String getCodeValue(){
        return codeValue;
    }
    public Date getIssueDate(){
        return issueDate;
    }
    public Date getUsedDate(){
        return usedDate;
    }
    public String getUsedUser(){
        return usedUser;
    }
    public String getUsedEmail(){
        return usedEmail;
    }
    public String getUseYn(){
        return useYn;
    }
    
    public void setUsedUser(String usedUser){
        this.usedUser = usedUser;
    }
    public void setCodeValue(String codeValue){
        this.codeValue = codeValue;
    }
    public void setIssueDate(Date issueDate){
        this.issueDate = issueDate;
    }
    public void setUsedDate(Date usedDate){
        this.usedDate = usedDate;
    }
    public void setUsedEmail(String usedEmail){
        this.usedEmail = usedEmail;
    }
    public void setUseYn(String useYn){
        this.useYn = useYn;
    }
    
}
