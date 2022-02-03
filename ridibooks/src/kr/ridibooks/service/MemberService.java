package kr.ridibooks.service;

import kr.ridibooks.model.MemberMarketingVO;
import kr.ridibooks.model.MemberVO;

public interface MemberService {
	// ȸ������
	public int register(MemberVO vo);
	// ȸ������ �� �� ������ ���� ���Ű��� 
	public int registerMarketing(MemberMarketingVO mVo);
	// ȸ�� id�� VO ��ȯ
	public MemberVO findByEmail(String id);
	public MemberVO login(MemberVO vo);
	public MemberVO idEmailReturnVO(MemberVO vo);
	public String idDoublecheck(String id);
	public String emailDoublecheck(String email);
	public int withdrawal(String id);
	public int resetPw(MemberVO vo);
	public int modifyEmail(MemberVO vo);
	public int modifyMarketing(MemberMarketingVO mVo);
}
