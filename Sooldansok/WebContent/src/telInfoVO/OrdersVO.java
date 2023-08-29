package telInfoVO;

import java.sql.Date;

public class OrdersVO {

	private int od_num;
	private String mem_id;
	private int pd_id;
	private int cnt;
	private String pay;
	private String delivery;
	private String info_name;
	private String info_price;
	private Date od_date;
	private String kind;
	private String country;
	private String alcohol;
	private int made_year;
	private int rev_num;
	private String comments;
	private int stars;

	public OrdersVO(int od_num, String mem_id, int pd_id, int cnt, String pay, String delivery, String info_name,
			String info_price, Date od_date) {
		this.od_num = od_num;
		this.mem_id = mem_id;
		this.pd_id = pd_id;
		this.cnt = cnt;
		this.pay = pay;
		this.delivery = delivery;
		this.info_name = info_name;
		this.info_price = info_price;
		this.od_date = od_date;
	}

	public OrdersVO(int od_num, Date od_date, String info_name, String info_price, String kind, String country,
			String alcohol, int made_year) {
		this.od_num = od_num;
		this.info_name = info_name;
		this.info_price = info_price;
		this.od_date = od_date;
		this.kind = kind;
		this.country = country;
		this.alcohol = alcohol;
		this.made_year = made_year;
	}

	public OrdersVO(int od_num, String delivery, int rev_num, String comments, int stars) {
		this.od_num = od_num;
		this.delivery = delivery;
		this.rev_num = rev_num;
		this.comments = comments;
		this.stars = stars;
	}

	public int getRev_num() {
		return rev_num;
	}

	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public int getMade_year() {
		return made_year;
	}

	public void setMade_year(int made_year) {
		this.made_year = made_year;
	}

	public String getInfo_price() {
		return info_price;
	}

	public void setInfo_price(String info_price) {
		this.info_price = info_price;
	}

	public String getInfo_name() {
		return info_name;
	}

	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}

	public int getOd_num() {
		return od_num;
	}

	public void setOd_num(int od_num) {
		this.od_num = od_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getPd_id() {
		return pd_id;
	}

	public void setPd_id(int pd_id) {
		this.pd_id = pd_id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Date getOd_date() {
		return od_date;
	}

	public void setOd_date(Date od_date) {
		this.od_date = od_date;
	}

}
