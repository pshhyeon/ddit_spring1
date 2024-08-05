package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
/*
 * @Repository는 데이터에 접근하는 클래스임을 명시한다
 * 해당 어노테이션이 있는 클래스는 스프링이 데이터를 관리하는 클래스라고 인지하여 자바 빈(Java Bean)으로 등록해서 관리한다.
 * 
 * SqlSessionTemplate 객체를 멤버 변수로 선언하는 이유는 mapper xml을 실행시키기 위해서다
 * 해당 객체 위헤 @Inject 또는 @Autowired를 붙여서 SqlSessionTemplate객체를 사용할 수 있도록 한다.
 * 이러한 형태를 '의존성 주입(DI)'이라고 한다. (필드 인젝션(Field Injection))
 * 
 * SqlSessionTemplate 객체는 new 키워드를 통해 직접 생성하지 않고,
 * 의존성 주입(Dependency Injection - DI)을 통해 주입받는다.
 * 스프링은 미리 만들어 놓은 SqlSessionTemplate 타입 객체를 BookDAO 객체에 주입한다.
 * 해당 과정은 스프링에서 자동 실행되며 개발자가 직접 SqlSessionTemplate 객체를 생성하는일 없이 곧바로 사용할 수 있다.
 * 
 * SqlSessisonTemplate 객체는 root-context.xml에서 정의해둔 객체이기도 하고,
 * 서버가 시작될 때 스프링은 미리 xml을 읽어 객체를 인스턴스화 해둔다. (메모리 탑재)
 */
@Repository
public class BookDAOImple implements IBookDAO {

	/*
	 * 매퍼 XML을 실행시키기 위해서 SqlSessionTemplate 객체를 멤버 변수로 선언한다.
	 * @Injection을 붙여서 Sql SessionTemplate 객체를 사용할 수 있게 한다.
	 */
	@Inject
	private SqlSessionTemplate sqlSession;
	/*
	 * sqlSession.insert()
	 * 1) 첫번째 파라미터는 SQL Mapper의 id이다.
	 * 	book_SQL.xml에서 namespace로 설정한 'Book'과 insert쿼리를 실행하기 위해 만든
	 *	insert문의 id의 값 'insertBook'이다.
	 *	mybatis는 네임스페이스 + id 조합으로 쿼리를 찾아서 실행한다.
	 * 2) 두번째 파라미터는 쿼리에 전달할 데이터이다.
	 * 	mapper내 insert쿼리를 실행하기 위해 전달되어지는 parameterType이 map이다.
	 */
	
	@Override
	public int insertBook(Map<String, Object> map) {
		return sqlSession.insert("Book.insertBook", map);
	}
	/*
	 * 요청에서부터 DAO까지 map에 title, category, price가 담겨져서 온다.
	 * 그리고, useGeneratedKeys와 keyProperty의 설정 덕분에 book 테이블의 pk인 book_id 항목이 새롭게 생성된다.
	 */

	@Override
	public int insertBook2(Map<String, Object> map) {
		/*
		 * useGeneratedKeys와 keyProperty 설정에 따라서 쿼리가 실행되고 나면 파라미터로
		 * 전달된 map 객체에 book테이블의 pk인 book_id 항목이 새롭게 생성된다.
		 * 
		 * [기존 Map] :::
		 * {
		 * 		"title" : "제목",
		 * 		"category" : "IT",
		 * 		"price" : 1000
		 * }
		 * [쿼리 실행 후  Map] :::
		 * {
		 * 		"title" : "제목",
		 * 		"category" : "IT",
		 * 		"price" : 1000,
		 * 		"book_id" : 2
		 * }
		 * sqlSessionTemplate.insert()의 반환값은 쿼리의 영향을 받은 행 수(row count)이다.
		 * insert 쿼리의 경우 성공하면 1개의 행(row)이 생기므로 1을 리턴하고 실패하면 0을 리턴한다.
		 */
		return sqlSession.insert("Book.insertBook2", map);
	}

	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		/*
		 * sqlSessionTemplate의 selectOne 메소드는 데이터를 한개만 가져올 때 사용한다.
		 * 만약 쿼리 결과 행 수가 0개면 selectOne 메소드는 null을 반환하게 되고,
		 * 쿼리 결과가 여러 개면 TooManyResultException 예외를 던진다.
		 * 우리가 작성한 쿼리는 조건이 pk이고, pk는 무조건 행이 유일함을 보장하기 때문에
		 * 결과는 0개 아니면 1개이다. 
		 */
		return sqlSession.selectOne("Book.selectBook", map);
	}

	@Override
	public int updateBook(Map<String, Object> map) {
		return sqlSession.update("Book.updateBook", map);
	}

	@Override
	public int deleteBook(Map<String, Object> map) {
		return sqlSession.delete("Book.deleteBook", map);
	}

	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return sqlSession.selectList("Book.selectBookList", map);
	}

}
