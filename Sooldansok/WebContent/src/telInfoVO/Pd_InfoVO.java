package telInfoVO;

import java.util.Date;

public class Pd_InfoVO {

	private int info_num;
	private String info_name;
	private String info_price;
	private String kind;
	private String country;
	private int capacity;
	private String alcohol;
	private int made_year;
	private int stock;
	private String img;

	public Pd_InfoVO(int info_num, String info_name, String info_price, String kind, String country, int capacity,
			String alcohol, int made_year, int stock, String img) {
		this.info_num = info_num;
		this.info_name = info_name;
		this.info_price = info_price;
		this.kind = kind;
		this.country = country;
		this.capacity = capacity;
		this.alcohol = alcohol;
		this.made_year = made_year;
		this.stock = stock;
		this.img = img;
	}

	public int getInfo_num() {
		return info_num;
	}

	public void setInfo_num(int info_num) {
		this.info_num = info_num;
	}

	public String getInfo_name() {
		return info_name;
	}

	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}

	public String getInfo_price() {
		return info_price;
	}

	public void setInfo_price(String info_price) {
		this.info_price = info_price;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
