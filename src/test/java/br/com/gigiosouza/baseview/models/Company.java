package br.com.gigiosouza.baseview.models;

public class Company {

	private Long id;
	private String name;
	private String companyCode;
	
	public Company() {}
	public Company(Long id, String name, String companyCode) {
		this.id = id;
		this.name = name;
		this.companyCode = companyCode;
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
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n  id:").append(id).append(",\n  name:").append(name).append(",\n  companyCode:")
				.append(companyCode).append("\n}");
		return builder.toString();
	}
}
