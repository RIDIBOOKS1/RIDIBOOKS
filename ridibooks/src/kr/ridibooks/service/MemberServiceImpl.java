package kr.ridibooks.service;

import kr.ridibooks.model.MemberDAO;
import kr.ridibooks.model.MemberMarketingVO;
import kr.ridibooks.model.MemberVO;
import kr.ridibooks.model.WithdrawalVO;

public class MemberServiceImpl implements MemberService {

	MemberDAO dao = new MemberDAO();

	@Override
	public int register(MemberVO vo) {
		return dao.memberInsert(vo);
	}

	@Override
	public int registerMarketing(MemberMarketingVO mVo) {
		return dao.memberMarketingInsert(mVo);
	}

	@Override
	public MemberVO findByEmail(String email) {
		return dao.memberFind(email);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return dao.memberLogin(vo);
	}

	@Override
	public MemberVO idEmailReturnVO(MemberVO vo) {
		return dao.idEmailReturnVO(vo);
	}

	@Override
	public String idDoublecheck(String id) {
		return dao.memberIdDoublecheck(id);
	}

	@Override
	public int withdrawaldel(String id) {
		return dao.memberDelete(id);
	}

	@Override
	public int resetPw(MemberVO vo) {
		return dao.updatePw(vo);
	}

	@Override
	public int modifyEmail(MemberVO vo) {
		return dao.updateEmail(vo);
	}

	@Override
	public String emailDoublecheck(String email) {
		return dao.memberEmailDoublecheck(email);
	}

	@Override
	public int modifyMarketing(MemberMarketingVO mVo) {
		return dao.updateMarketing(mVo);
	}

	@Override
	public int idReturnPk(String id) {
		return dao.idReturnPk(id);
	}

	@Override
	public int withdrawal(WithdrawalVO wVo) {
		return dao.withdrawalInsert(wVo);
	}

	@Override
	public int modifyIsMember(MemberVO vo) {
		return dao.updateIsMember(vo);
	}

}
