package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVO {
	private int noticeNo;			// 공지사항게시판번호
	private String noticeTitle;		// 공지사항게시판제목
	private String noticeWriter;	// 공지사항게시판작성자
	private String noticeContent;	// 공지사항게시판내용
	private String noticeDate;		// 공지사항게시판작성일
	private int noticeHit;			// 공지사항게시판조회수
}
