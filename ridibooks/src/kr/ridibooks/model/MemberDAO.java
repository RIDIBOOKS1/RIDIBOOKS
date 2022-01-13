package kr.ridibooks.model;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private static SqlSessionFactory sqlSessionFactory; 
	 
	   static {
		   try {
			   String resource = "kr/ridibooks/mybatis/config.xml";
			   InputStream inputStream = Resources.getResourceAsStream(resource);
			   sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream); 
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
		public int memberInsert(MemberVO vo) {
			SqlSession session = sqlSessionFactory.openSession();
			int cnt = session.insert("memberInsert", vo);
			session.commit();
			session.close(); // ¹Ý³³
			return cnt;
		}
		
		public String memberLogin(MemberVO vo) {
		   SqlSession session=sqlSessionFactory.openSession();
		   String id = session.selectOne("memberLogin", vo);
		   session.close();
		   return id;
		}
		
		public MemberVO memberFind(String id) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("id", id);
			 session.close();
			 return selectedVo;
		}
}
