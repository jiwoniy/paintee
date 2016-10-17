package com.paintee.mobile.test.controller;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class TestVO implements Serializable {
	private static final long serialVersionUID = -9103756160491823835L;

	private MultipartFile painteeFile;
	private String displayName;

	public MultipartFile getPainteeFile() {
		return painteeFile;
	}

	public void setPainteeFile(MultipartFile painteeFile) {
		this.painteeFile = painteeFile;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
