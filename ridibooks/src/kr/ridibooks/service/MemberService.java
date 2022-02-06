package kr.ridibooks.service;

import kr.ridibooks.model.MemberMarketingVO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.model.WithdrawalVO;

public interface MemberService {
	// ȸ������
	public int register(MemberVO vo);
	// ȸ������ �� �� ������ ���� ���Ű��� 
	public int registerMarketing(MemberMarketingVO mVo);
	// ȸ�� �̸��Ϸη� VO ��ȯ
	public MemberVO findByEmail(String id);
	// �α���
	public MemberVO login(MemberVO vo);
	// memberVO id�� �̸��Ϸ� memberVO ����
	public MemberVO idEmailReturnVO(MemberVO vo);
	public String idDoublecheck(String id);
	public String emailDoublecheck(String email);
	public int idReturnPk(String id);
	public int withdrawaldel(String id);
	public int resetPw(MemberVO vo);
	public int modifyEmail(MemberVO vo);
	public int modifyMarketing(MemberMarketingVO mVo);
	public int withdrawal(WithdrawalVO wVo);
	public int modifyIsMember(MemberVO vo);
}
