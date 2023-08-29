package telInfoVO;

import java.util.Date;

public class ClientVO {

	private String id;
	private String pw;
	private String name;
	private Date birth;
	private String address;
	private String email;
	private String phone;

	public ClientVO() {
	}

	public ClientVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public ClientVO(String id, String pw, String name, Date birth, String address, String email, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
