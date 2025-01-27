package kr.or.ddit.book.service;

import java.util.List;
import java.util.Map;

public interface IBookService {
	public int insertBook(Map<String, Object> map);
	public String insertBook2(Map<String, Object> map);
	public Map<String, Object> selectBook(Map<String, Object> map);
	public boolean updateBook(Map<String, Object> map);
	public boolean deleteBook(Map<String, Object> map);
	public List<Map<String, Object>> selectBookList(Map<String, Object> map);
}
