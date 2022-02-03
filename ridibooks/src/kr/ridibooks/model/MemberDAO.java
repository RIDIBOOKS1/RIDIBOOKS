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
	   
	   // ȸ�� ����
		public int memberInsert(MemberVO vo) {
			SqlSession session = sqlSessionFactory.openSession();
			int cnt = session.insert("memberInsert", vo);
			session.commit();
			session.close(); // �ݳ�
			return cnt;
		}
		
		// ������ ���� insert
		public int memberMarketingInsert(MemberMarketingVO mVo) {
			SqlSession session = sqlSessionFactory.openSession();
			int cnt = session.insert("memberMarketingInsert", mVo);
			session.commit();
			session.close(); // �ݳ�
			return cnt;
		}
		
		// �α���
		public MemberVO memberLogin(MemberVO vo) {
		   SqlSession session=sqlSessionFactory.openSession();
		   MemberVO searchedVO = session.selectOne("memberLogin", vo);
		   session.close();
		   return searchedVO;
		}
		
		// �̸��Ϸ� ��� ��ȯ
		public MemberVO memberFind(String email) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("memberFind", email);
			 session.close();
			 return selectedVo;
		}
		
		// id�� �̸��� ������ MemberVO �� �������ִ� �޼���
		public MemberVO idEmailReturnVO(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 MemberVO selectedVo = session.selectOne("idEmailReturnVO", vo);
			 session.close();
			 return selectedVo;
		}
		
		
		// ���� �� �ϳ��� ��ġ��
		// ��й�ȣ ����
		public int updatePw(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 int cnt=session.update("updatePw", vo);
			 session.commit();
			 session.close();
			 return cnt;
		}
		
		// �̸��� ����
		public int updateEmail(MemberVO vo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 int cnt=session.update("updateEmail", vo);
			 session.commit();
			 session.close();
			 return cnt;
		}
		
		// ������ ���� ���Ű��� ����
		public int updateMarketing(MemberMarketingVO mVo) {
			 SqlSession session=sqlSessionFactory.openSession();
			 int cnt=session.update("updateMarketing", mVo);
			 session.commit();
			 session.close();
			 return cnt;
		}
		
		// �ߺ�Ȯ�� �ϳ��� ��ġ��
		// ���̵� �ߺ�Ȯ��
	   public String memberIdDoublecheck(String id) {
		   SqlSession session=sqlSessionFactory.openSession();
		   String dbId=session.selectOne("memberIdDoublecheck", id);// id or null
		   String idDouble="NO";
		   if(dbId!=null) {
			   idDouble="YES";
		   }
		   return idDouble; // YES(�ߺ�), NO(�ߺ��ƴ�)
	   }
	   
	   // �̸��� �ߺ� Ȯ��
		public String memberEmailDoublecheck(String email) {
			SqlSession session=sqlSessionFactory.openSession();
			String dbEmail=session.selectOne("memberEmailDoublecheck", email);// email or null
			String emailDouble="NO";
			if(dbEmail!=null) {
				emailDouble="YES";
			}
			return emailDouble; // YES(�ߺ�), NO(�ߺ��ƴ�)
		}
	   
	   // ȸ��Ż��
	   public int memberDelete(String id) {
		   SqlSession session=sqlSessionFactory.openSession();
		   int cnt=session.delete("memberDelete", id);
		   session.commit();
		   session.close();
		   return cnt;
	   }
	   
	   
}
