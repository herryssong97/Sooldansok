package telInfoVO;

public class ReviewVO {

	private int rev_num;
	private int pd_id;
	private String pd_name;
	private String mem_id;
	private String comments;
	private int stars;
	private int od_num;

	public int getOd_num() {
		return od_num;
	}

	public void setOd_num(int od_num) {
		this.od_num = od_num;
	}

	public ReviewVO() {
	}

	public ReviewVO(int rev_num, String pd_name, String mem_id, String comments, int stars, int od_num) {
		this.rev_num = rev_num;
		this.pd_name = pd_name;
		this.mem_id = mem_id;
		this.comments = comments;
		this.stars = stars;
		this.od_num = od_num;
	}

	// 상품별 리뷰 조회를 위한 생성자
	public ReviewVO(String mem_id, String comments, int stars) {
		this.mem_id = mem_id;
		this.comments = comments;
		this.stars = stars;
	}

	public ReviewVO(int rev_num, String mem_id, String comments, int stars, int od_num) {
		this.rev_num = rev_num;
		this.mem_id = mem_id;
		this.comments = comments;
		this.stars = stars;
		this.od_num = od_num;
	}

	public int getRev_num() {
		return rev_num;
	}

	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}

	public int getPd_id() {
		return pd_id;
	}

	public void setPd_id(int pd_id) {
		this.pd_id = pd_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

}
