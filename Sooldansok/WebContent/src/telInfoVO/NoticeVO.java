package telInfoVO;

import java.sql.Date;

public class NoticeVO {

	private int n_num;
	private String n_title;
	private String n_comment;
	private String mem_id;
	private Date n_date;

	public Date getN_date() {
		return n_date;
	}

	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}

	public NoticeVO(int n_num, String n_title, String n_comment, String mem_id, Date n_date) {
		this.n_num = n_num;
		this.n_title = n_title;
		this.n_comment = n_comment;
		this.mem_id = mem_id;
		this.n_date = n_date;
	}

	public int getN_num() {
		return n_num;
	}

	public void setN_num(int n_num) {
		this.n_num = n_num;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_comment() {
		return n_comment;
	}

	public void setN_comment(String n_comment) {
		this.n_comment = n_comment;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

}
