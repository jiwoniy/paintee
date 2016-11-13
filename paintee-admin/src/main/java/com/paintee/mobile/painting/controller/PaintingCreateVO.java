/**
@file PaintingCreateVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingCreateVO.java |    
| Package | com.paintee.mobile.painting.controller |    
| Project name | paintee-admin |    
| Type name | PaintingCreateVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 30. 오후 1:55:37 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.painting.controller;

import org.springframework.web.multipart.MultipartFile;

import com.paintee.common.repository.entity.Painting;

/**
@class PaintingCreateVO
com.paintee.mobile.painting.controller \n
   ㄴ PaintingCreateVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 30. 오후 1:55:37 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 옆서 그림 생성 VO
*/
public class PaintingCreateVO extends Painting {
	private static final long serialVersionUID = 424539039599217912L;

	private MultipartFile painteeFile;

	public MultipartFile getPainteeFile() {
		return painteeFile;
	}

	public void setPainteeFile(MultipartFile painteeFile) {
		this.painteeFile = painteeFile;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxWidth() {
		return xWidth;
	}

	public void setxWidth(int xWidth) {
		this.xWidth = xWidth;
	}

	public int getyWidth() {
		return yWidth;
	}

	public void setyWidth(int yWidth) {
		this.yWidth = yWidth;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	private int x;
    
    private int y;
    
    private int xWidth;
    
    private int yWidth;
    
    private int rotate;
}
