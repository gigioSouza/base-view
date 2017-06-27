package br.com.gigiosouza.baseview.views;

import br.com.gigiosouza.baseview.ModelProp;

public class CompanyView {

	@ModelProp(value="id")
	private Long identificadorDaEmpresa;
	
	@ModelProp(value="name")
	private String nomeDaEmpresa;
	
	@ModelProp(value="companyCode")
	private String cnpjDaEmpresa;

	public Long getIdentificadorDaEmpresa() {
		return identificadorDaEmpresa;
	}

	public void setIdentificadorDaEmpresa(Long identificadorDaEmpresa) {
		this.identificadorDaEmpresa = identificadorDaEmpresa;
	}

	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}

	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}

	public String getCnpjDaEmpresa() {
		return cnpjDaEmpresa;
	}

	public void setCnpjDaEmpresa(String cnpjDaEmpresa) {
		this.cnpjDaEmpresa = cnpjDaEmpresa;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n  identificadorDaEmpresa:").append(identificadorDaEmpresa).append(",\n  nomeDaEmpresa:")
				.append(nomeDaEmpresa).append(",\n  cnpjDaEmpresa:").append(cnpjDaEmpresa).append("\n}");
		return builder.toString();
	}
	
}