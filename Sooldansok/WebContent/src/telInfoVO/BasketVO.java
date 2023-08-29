package telInfoVO;

public class BasketVO {

	private int bas_num;
	private String mem_id;
	private String pd_name;
	private String price;
	private int cnt;
	private String totprice;
	private int pd_id;// 조인

	public BasketVO(int bas_num, String mem_id, int pd_id, String price, int cnt, String pd_name, String totprice) {
		this.bas_num = bas_num;
		this.mem_id = mem_id;
		this.pd_id = pd_id;
		this.pd_name = pd_name;
		this.cnt = cnt;
		this.price = price;
		this.totprice = totprice;
	}

	public int getBas_num() {
		return bas_num;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public void setBas_num(int bas_num) {
		this.bas_num = bas_num;
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

	public String getTotprice() {
		return totprice;
	}

	public void setTotprice(String totprice) {
		this.totprice = totprice;
	}

}
