package br.com.gigiosouza.baseview.views;

import br.com.gigiosouza.baseview.ModelProp;

public class UserView {

	@ModelProp(value="id")
	private Long identificadorDoUsuario;
	
	@ModelProp(value="name")
	private String nomeDoUsuario;
	
	@ModelProp(value="email")
	private String emailDoUsuario;
	
	@ModelProp(value="company.name", group="list")
	private String nomeDaEmpresa;

	@ModelProp(value="company", group="full", view = true)
	private CompanyView empresaDoUsuario;
	
	public Long getIdentificadorDoUsuario() {
		return identificadorDoUsuario;
	}

	public void setIdentificadorDoUsuario(Long identificadorDoUsuario) {
		this.identificadorDoUsuario = identificadorDoUsuario;
	}

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public String getEmailDoUsuario() {
		return emailDoUsuario;
	}

	public void setEmailDoUsuario(String emailDoUsuario) {
		this.emailDoUsuario = emailDoUsuario;
	}

	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}

	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}

	public CompanyView getEmpresaDoUsuario() {
		return empresaDoUsuario;
	}

	public void setEmpresaDoUsuario(CompanyView empresaDoUsuario) {
		this.empresaDoUsuario = empresaDoUsuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n  identificadorDoUsuario:").append(identificadorDoUsuario).append(",\n  nomeDoUsuario:")
				.append(nomeDoUsuario).append(",\n  empresaDoUsuario:").append(empresaDoUsuario)
				.append(",\n  emailDoUsuario:").append(emailDoUsuario).append(",\n  nomeDaEmpresa:")
				.append(nomeDaEmpresa).append("\n}");
		return builder.toString();
	}
	
}
