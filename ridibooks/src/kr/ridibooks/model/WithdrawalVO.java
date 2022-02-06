package kr.ridibooks.model;

public class WithdrawalVO {
	private int memberInfo_num;
	private int noBook;
	private int noBenefit;
	private int systemError;
	private int lateResponse;
	private int noUse;
	private int concernedPs;
	private int etc;
	private String pw;
	private int withdrawalAgree;

	public int getMemberInfo_num() {
		return memberInfo_num;
	}

	public void setMemberInfo_num(int memberInfo_num) {
		this.memberInfo_num = memberInfo_num;
	}

	public int getNoBook() {
		return noBook;
	}

	public void setNoBook(int noBook) {
		this.noBook = noBook;
	}

	public int getNoBenefit() {
		return noBenefit;
	}

	public void setNoBenefit(int noBenefit) {
		this.noBenefit = noBenefit;
	}

	public int getSystemError() {
		return systemError;
	}

	public void setSystemError(int systemError) {
		this.systemError = systemError;
	}

	public int getLateResponse() {
		return lateResponse;
	}

	public void setLateResponse(int lateResponse) {
		this.lateResponse = lateResponse;
	}

	public int getNoUse() {
		return noUse;
	}

	public void setNoUse(int noUse) {
		this.noUse = noUse;
	}

	public int getConcernedPs() {
		return concernedPs;
	}

	public void setConcernedPs(int concernedPs) {
		this.concernedPs = concernedPs;
	}

	public int getEtc() {
		return etc;
	}

	public void setEtc(int etc) {
		this.etc = etc;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getWithdrawalAgree() {
		return withdrawalAgree;
	}

	public void setWithdrawalAgree(int withdrawalAgree) {
		this.withdrawalAgree = withdrawalAgree;
	}
}
