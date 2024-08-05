package kr.or.ddit.vo;

import lombok.Data;

@Data
public class FreeVO {
	private int freeNo;				// 자유게시판번호
	private String freeTitle;		// 자유게시판제목
	private String freeWriter;		// 자유게시판작성자
	private String freeContent;		// 자유게시판내용
	private String freeDate;		// 자유게시판작성일
	private int freeHit;			// 자유게시판조회수

}
