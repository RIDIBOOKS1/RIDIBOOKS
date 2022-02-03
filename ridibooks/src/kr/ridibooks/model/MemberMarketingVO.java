package kr.ridibooks.model;

public class MemberMarketingVO {
	private String subEmail;
	private String memberInfoId;
	private int emailagree;
	private int appagree;
	private int appnightagree;

	public String getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}

	public int getEmailagree() {
		return emailagree;
	}

	public String getMemberInfoId() {
		return memberInfoId;
	}

	public void setMemberInfoId(String memberInfoId) {
		this.memberInfoId = memberInfoId;
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
