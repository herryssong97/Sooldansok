package telInfoVO;

import java.sql.Date;

public class QuestionVO {

	private int q_num;
	private String q_title;
	private String q_comment;
	private String answer;
	private String mem_id;
	private Date q_date;
	private Date answer_date;

	public QuestionVO() {
	}

	// TelInfoDAO 를 위해
	public QuestionVO(int q_num, String q_title, String q_comment, String answer, String mem_id) {
		this.q_num = q_num;
		this.q_title = q_title;
		this.q_comment = q_comment;
		this.answer = answer;
		this.mem_id = mem_id;
	}

	public QuestionVO(int q_num, String q_title, String q_comment, String answer, String mem_id, Date q_date,
			Date answer_date) {
		this.q_num = q_num;
		this.q_title = q_title;
		this.q_comment = q_comment;
		this.answer = answer;
		this.mem_id = mem_id;
		this.q_date = q_date;
		this.answer_date = answer_date;
	}

	public Date getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}

	public Date getQ_date() {
		return q_date;
	}

	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}

	public int getQ_num() {
		return q_num;
	}

	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_comment() {
		return q_comment;
	}

	public void setQ_comment(String q_comment) {
		this.q_comment = q_comment;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

}
