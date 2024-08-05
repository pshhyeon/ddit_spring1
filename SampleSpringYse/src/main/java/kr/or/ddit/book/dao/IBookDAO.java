package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

public interface IBookDAO {

	public int insertBook(Map<String, Object> map);
	public int insertBook2(Map<String, Object> map);
	public Map<String, Object> selectBook(Map<String, Object> map);
	public int updateBook(Map<String, Object> map);
	public int deleteBook(Map<String, Object> map);
	public List<Map<String, Object>> selectBookList(Map<String, Object> map);

}
