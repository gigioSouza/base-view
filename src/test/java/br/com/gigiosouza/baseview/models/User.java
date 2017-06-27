package br.com.gigiosouza.baseview.models;

public class User {

	private Long id;
	private String name;
	private String email;
	private Company company;
	
	public User() {}
	public User(Long id, String name, String email, Company company) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.company = company;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n  id:").append(id).append(",\n  name:").append(name).append(",\n  email:").append(email)
				.append(",\n  company:").append(company).append("\n}");
		return builder.toString();
	}
	
	
}
