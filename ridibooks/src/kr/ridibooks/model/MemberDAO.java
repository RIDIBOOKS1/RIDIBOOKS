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
		
		public MemberVO memberLogin(MemberVO vo) {
		   SqlSession session=sqlSessionFactory.openSession();
		   MemberVO searchedVO = session.selectOne("memberLogin", vo);
		   session.close();
		   return searchedVO;
		}
		
		public MemberVO memberFind(String email) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("memberFind", email);
			 session.close();
			 return selectedVo;
		}
		
		public MemberVO findPw(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("findPw", vo);
			 session.close();
			 return selectedVo;
		}
		
		// Áßº¹È®ÀÎ
	   public String memberIdDoublecheck(String id) {
		   SqlSession session=sqlSessionFactory.openSession();
		   String dbId=session.selectOne("memberIdDoublecheck", id);// id or null
		   String idDouble="NO";
		   if(dbId!=null) {
			   idDouble="YES";
		   }
		   return idDouble; // YES(Áßº¹), NO(Áßº¹¾Æ´Ô)
	   }
	   
	   // È¸¿øÅ»Åð
	   public int memberDelete(String id) {
		   SqlSession session=sqlSessionFactory.openSession();
		   int cnt=session.delete("memberDelete", id);
		   session.commit();
		   session.close();
		   return cnt;
	   }
}
