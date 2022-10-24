//게시판 정보를 저장하는 클래스
package vo;

import java.util.Date;

public class BoardBean {
	private int BOARD_NUM; //글 번호
	private String BOARD_NAME;	//글 작성자
	private String BOARD_PASS;	//글 비밀번호
	private String BOARD_SUBJECT;	//글 제목
	private String BOARD_CONTENT;	//글 내용
	private String BOARD_FILE;	//첨부 파일
	private int RE_REF;		//관련글 번호
	private int RE_LEV;		//답글 레벨
	private int RE_STEP;	//관련글 중 출력 순서
	private int BOARD_READCOUNT;	//조회수
	private Date BOARD_DATE;		//작성일
	
	//get~set 메서드
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}
	public String getBOARD_PASS() {
		return BOARD_PASS;
	}
	public void setBOARD_PASS(String bOARD_PASS) {
		BOARD_PASS = bOARD_PASS;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	public String getBOARD_FILE() {
		return BOARD_FILE;
	}
	public void setBOARD_FILE(String bOARD_FILE) {
		BOARD_FILE = bOARD_FILE;
	}
	public int getRE_REF() {
		return RE_REF;
	}
	public void setRE_REF(int rE_REF) {
		RE_REF = rE_REF;
	}
	public int getRE_LEV() {
		return RE_LEV;
	}
	public void setRE_LEV(int rE_LEV) {
		RE_LEV = rE_LEV;
	}
	public int getRE_STEP() {
		return RE_STEP;
	}
	public void setRE_STEP(int rE_STEP) {
		RE_STEP = rE_STEP;
	}
	public int getBOARD_READCOUNT() {
		return BOARD_READCOUNT;
	}
	public void setBOARD_READCOUNT(int bOARD_READCOUNT) {
		BOARD_READCOUNT = bOARD_READCOUNT;
	}
	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(Date bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}
	
	
}
