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
	   
	   // 회원 가입
		public int memberInsert(MemberVO vo) {
			SqlSession session = sqlSessionFactory.openSession();
			int cnt = session.insert("memberInsert", vo);
			session.commit();
			session.close(); // 반납
			return cnt;
		}
		
		// 마케팅 정보 insert
		public int memberMarketingInsert(MemberMarketingVO mVo) {
			SqlSession session = sqlSessionFactory.openSession();
			int cnt = session.insert("memberMarketingInsert", mVo);
			session.commit();
			session.close(); // 반납
			return cnt;
		}
		
		// 로그인
		public MemberVO memberLogin(MemberVO vo) {
		   SqlSession session=sqlSessionFactory.openSession();
		   MemberVO searchedVO = session.selectOne("memberLogin", vo);
		   session.close();
		   return searchedVO;
		}
		
		// 이메일로 멤버 반환
		public MemberVO memberFind(String email) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("memberFind", email);
			 session.close();
			 return selectedVo;
		}
		
		// id와 이메일 값으로 MemberVO 를 리턴해주는 메서드
		public MemberVO idEmailReturnVO(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("idEmailReturnVO", vo);
			 session.close();
			 return selectedVo;
		}
		
		
		// 변경 문 하나로 합치기
		// 비밀번호 변경
		public int updatePw(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 int cnt=session.update("updatePw", vo);
			 session.commit();
			 session.close();
			 return cnt;
		}
		
		// 이메일 변경
		public int updateEmail(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 int cnt=session.update("updateEmail", vo);
			 session.commit();
			 session.close();
			 return cnt;
		}
		
		// 마케팅 정보 수신관리 변경
		public int updateMarketing(MemberMarketingVO mVo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 int cnt=session.update("updateMarketing", mVo);
			 session.commit();
			 session.close();
			 return cnt;
		}
		
		// 중복확인 하나로 합치기
		// 아이디 중복확인
	   public String memberIdDoublecheck(String id) {
		   SqlSession session=sqlSessionFactory.openSession();
		   String dbId=session.selectOne("memberIdDoublecheck", id);// id or null
		   String idDouble="NO";
		   if(dbId!=null) {
			   idDouble="YES";
		   }
		   return idDouble; // YES(중복), NO(중복아님)
	   }
	   
	   // 이메일 중복 확인
		public String memberEmailDoublecheck(String email) {
			SqlSession session=sqlSessionFactory.openSession();
			String dbEmail=session.selectOne("memberEmailDoublecheck", email);// email or null
			String emailDouble="NO";
			if(dbEmail!=null) {
				emailDouble="YES";
			}
			return emailDouble; // YES(중복), NO(중복아님)
		}
	   
	   // 회원탈퇴
	   public int memberDelete(String id) {
		   SqlSession session=sqlSessionFactory.openSession();
		   int cnt=session.delete("memberDelete", id);
		   session.commit();
		   session.close();
		   return cnt;
	   }
	   
	   
}
