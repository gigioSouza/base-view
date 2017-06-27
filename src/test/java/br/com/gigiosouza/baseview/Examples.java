package br.com.gigiosouza.baseview;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.gigiosouza.baseview.models.Company;
import br.com.gigiosouza.baseview.models.User;
import br.com.gigiosouza.baseview.views.CompanyView;
import br.com.gigiosouza.baseview.views.UserView;

public class Examples {
	
	@Test
	public void fromModel() {
		User user = this.user1();
		
		UserView userView = BaseView.fromModel(user, new UserView());
		
		this.assertFromUser(userView, user);
	}
	
	@Test
	public void fromModelGroupFull() {
		User user = this.user1();
		
		UserView userView = BaseView.fromModel(user, new UserView(), "full");
		
		this.assertFromUserFull(userView, user);
	}
	
	@Test
	public void fromModelGroupList() {
		User user1 = this.user1();
		User user2 = this.user2();
		
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		
		List<UserView> userViewList = BaseView.fromModel(userList, UserView.class, "list");
		
		Assert.assertNotNull(userViewList);
		Assert.assertEquals(userViewList.size(), 2);
		
		for (int i = 0; i < userViewList.size(); i++) {
			this.assertFromUserList(userViewList.get(i), userList.get(i));
		}
	}
	
	@Test
	public void toModel() {
		UserView userView = this.userView1();
		
		User user = BaseView.toModel(userView, new User());
		
		this.assertToUser(userView, user);
	}
	
	@Test
	public void toModelGroupFull() {
		UserView userView = this.userView1();
		
		User user = BaseView.toModel(userView, new User(), "full");
		
		this.assertToUserFull(userView, user);
	}
	
	@Test
	public void toModelGroupList() {
		UserView userView1 = this.userView1();
		UserView userView2 = this.userView2();
		
		List<UserView> userViewList = new ArrayList<UserView>();
		userViewList.add(userView1);
		userViewList.add(userView2);
		
		List<User> userList = BaseView.toModel(userViewList, User.class, "list");
		
		Assert.assertNotNull(userViewList);
		Assert.assertEquals(userViewList.size(), 2);
		
		for (int i = 0; i < userViewList.size(); i++) {
			this.assertToUserList(userViewList.get(i), userList.get(i));
		}
	}
	
	private void assertFromUser(UserView userView, User user) {
		Assert.assertEquals(userView.getNomeDoUsuario(), user.getName());
		Assert.assertEquals(userView.getEmailDoUsuario(), user.getEmail());
		Assert.assertEquals(userView.getIdentificadorDoUsuario(), user.getId());
		Assert.assertNull(userView.getEmpresaDoUsuario());
		Assert.assertNull(userView.getNomeDaEmpresa());
	}
	
	private void assertFromUserFull(UserView userView, User user) {
		Assert.assertEquals(userView.getNomeDoUsuario(), user.getName());
		Assert.assertEquals(userView.getEmailDoUsuario(), user.getEmail());
		Assert.assertEquals(userView.getIdentificadorDoUsuario(), user.getId());
		Assert.assertNull(userView.getNomeDaEmpresa());
		Assert.assertNotNull(userView.getEmpresaDoUsuario());
		Assert.assertEquals(userView.getEmpresaDoUsuario().getNomeDaEmpresa(), user.getCompany().getName());
		Assert.assertEquals(userView.getEmpresaDoUsuario().getCnpjDaEmpresa(), user.getCompany().getCompanyCode());
		Assert.assertEquals(userView.getEmpresaDoUsuario().getIdentificadorDaEmpresa(), user.getCompany().getId());
	}
	
	private void assertFromUserList(UserView userView, User user) {
		Assert.assertEquals(userView.getNomeDoUsuario(), user.getName());
		Assert.assertEquals(userView.getEmailDoUsuario(), user.getEmail());
		Assert.assertEquals(userView.getIdentificadorDoUsuario(), user.getId());
		Assert.assertEquals(userView.getNomeDaEmpresa(), user.getCompany().getName());
		Assert.assertNull(userView.getEmpresaDoUsuario());
	}
	
	private void assertToUser(UserView userView, User user) {
		Assert.assertEquals(user.getName(), userView.getNomeDoUsuario());
		Assert.assertEquals(user.getEmail(), userView.getEmailDoUsuario());
		Assert.assertEquals(user.getId(), userView.getIdentificadorDoUsuario());
		Assert.assertNull(user.getCompany());
	}
	
	private void assertToUserFull(UserView userView, User user) {
		Assert.assertEquals(user.getName(), userView.getNomeDoUsuario());
		Assert.assertEquals(user.getEmail(), userView.getEmailDoUsuario());
		Assert.assertEquals(user.getId(), userView.getIdentificadorDoUsuario());
		Assert.assertNotNull(user.getCompany());
		Assert.assertEquals(user.getCompany().getName(), userView.getEmpresaDoUsuario().getNomeDaEmpresa());
		Assert.assertEquals(user.getCompany().getCompanyCode(), userView.getEmpresaDoUsuario().getCnpjDaEmpresa());
		Assert.assertEquals(user.getCompany().getId(), userView.getEmpresaDoUsuario().getIdentificadorDaEmpresa());
	}
	
	private void assertToUserList(UserView userView, User user) {
		Assert.assertEquals(user.getName(), userView.getNomeDoUsuario());
		Assert.assertEquals(user.getEmail(), userView.getEmailDoUsuario());
		Assert.assertEquals(user.getId(), userView.getIdentificadorDoUsuario());
		Assert.assertNotNull(user.getCompany());
		Assert.assertEquals(user.getCompany().getName(), userView.getEmpresaDoUsuario().getNomeDaEmpresa());
		Assert.assertNull(user.getCompany().getCompanyCode());
		Assert.assertNull(user.getCompany().getId());
	}
	
	private User user1() {
		return new User(1l, "user1", "user1@mail1.com", this.company1());
	}
	
	private Company company1() {
		return new Company(1l, "company1", "12345");	
	}
	
	private User user2() {
		return new User(2l, "user2", "user2@mail2.com", this.company2());
	}
	
	private Company company2() {
		return new Company(2l, "company2", "1234567890");	
	}
	
	private UserView userView1() {
		UserView userView1 = new UserView();
		userView1.setIdentificadorDoUsuario(1l);
		userView1.setNomeDoUsuario("user1");
		userView1.setEmailDoUsuario("user1@mail1.com");
		userView1.setNomeDaEmpresa("company1");
		userView1.setEmpresaDoUsuario(this.companyView1());
		return userView1;
	}
	
	private CompanyView companyView1() {
		CompanyView companyView1 = new CompanyView();
		companyView1.setIdentificadorDaEmpresa(1l);
		companyView1.setNomeDaEmpresa("company1");
		companyView1.setCnpjDaEmpresa("12345");
		return companyView1;
	}
	
	private UserView userView2() {
		UserView userView1 = new UserView();
		userView1.setIdentificadorDoUsuario(1l);
		userView1.setNomeDoUsuario("user2");
		userView1.setEmailDoUsuario("user2@mail2.com");
		userView1.setNomeDaEmpresa("company2");
		userView1.setEmpresaDoUsuario(this.companyView2());
		return userView1;
	}
	
	private CompanyView companyView2() {
		CompanyView companyView1 = new CompanyView();
		companyView1.setIdentificadorDaEmpresa(2l);
		companyView1.setNomeDaEmpresa("company2");
		companyView1.setCnpjDaEmpresa("1234567890");
		return companyView1;
	}
}
