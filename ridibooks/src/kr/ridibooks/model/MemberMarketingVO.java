package kr.ridibooks.model;

public class MemberMarketingVO {
	private int memberInfo_num;
	private String subEmail;
	private int emailagree;
	private int appagree;
	private int appnightagree;

	public int getMemberInfo_num() {
		return memberInfo_num;
	}

	public void setMemberInfo_num(int memberInfo_num) {
		this.memberInfo_num = memberInfo_num;
	}

	public String getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}

	public int getEmailagree() {
		return emailagree;
	}

	public void setEmailagree(int emailagree) {
		this.emailagree = emailagree;
	}

	public int getAppagree() {
		return appagree;
	}

	public void setAppagree(int appagree) {
		this.appagree = appagree;
	}

	public int getAppnightagree() {
		return appnightagree;
	}

	public void setAppnightagree(int appnightagree) {
		this.appnightagree = appnightagree;
	}

}
