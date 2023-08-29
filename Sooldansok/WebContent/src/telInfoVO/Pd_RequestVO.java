package telInfoVO;

import java.util.Date;

public class Pd_RequestVO {

	// 요청사항, 주문상태 컬럼 추가
	private int req_num;
	private String mem_id;
	private String req_name;
	private String req_country;
	private int req_made_year;
	private String req_comment;
	private String req_state;

	public Pd_RequestVO() {
	}

	public Pd_RequestVO(int req_num, String mem_id, String req_name, String req_country, int req_made_year,
			String req_comment, String req_state) {
		super();
		this.req_num = req_num;
		this.mem_id = mem_id;
		this.req_name = req_name;
		this.req_country = req_country;
		this.req_made_year = req_made_year;
		this.req_comment = req_comment;
		this.req_state = req_state;
	}

	public int getReq_num() {
		return req_num;
	}

	public void setReq_num(int req_num) {
		this.req_num = req_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getReq_name() {
		return req_name;
	}

	public void setReq_name(String req_name) {
		this.req_name = req_name;
	}

	public String getReq_country() {
		return req_country;
	}

	public void setReq_country(String req_country) {
		this.req_country = req_country;
	}

	public int getReq_made_year() {
		return req_made_year;
	}

	public void setReq_made_year(int req_made_year) {
		this.req_made_year = req_made_year;
	}

	public String getReq_comment() {
		return req_comment;
	}

	public void setReq_comment(String req_comment) {
		this.req_comment = req_comment;
	}

	public String getReq_state() {
		return req_state;
	}

	public void setReq_state(String req_state) {
		this.req_state = req_state;
	}

}
