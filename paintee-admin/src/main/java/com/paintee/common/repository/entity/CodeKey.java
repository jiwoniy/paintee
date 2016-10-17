package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;

public class CodeKey extends BaseEntity {
    private String codeGroup;

    private String codeValue;

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}