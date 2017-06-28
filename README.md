# base-view
A easy way to transfer data between different Objects in Java, take a look:

Models
```
class User {
  Long id;
  String name;
  String email;
  Company company;
}

class Company {
  Long id;
  String name;
  String code;
}
```

Having those Models, I want to send another object to the Json Serializer to respond a request. I want to respond those Views from my Models

Views
```
class UserView {
  Long userId;
  String userName;
  String userEmail;
  
  // When editing the User, I want to respond the CompanyView
  CompanyView userCompany;
  
  // When listing the User, I want to respond only the Company's name
  String userCompanyName;
}

class CompanyView {
  Long companyId;
  String companyName;
  String companyCode;
}
```

Now, I need to map the data from the Views:
```
class UserView {
  @ModelProp(value="id")
  Long userId;
  
  @ModelProp(value="name")
  String userName;
  
  @ModelProp(value="email")
  String userEmail;
  
  @ModelProp(value="company", group="editing", view=true)
  CompanyView userCompany;
  
  @ModelProp(value="company.name", group="list")
  String userCompanyName; 
}

class CompanyView {
  @ModelProp(value="id")
  Long companyId;
  
  @ModelProp(value="name")
  String companyName;
  
  @ModelProp(value="code")
  String companyCode;
}
```

Using the **@ModelProp** I tell the **BaseView** the mapping of the View.

**value** is the name of the property on the model I'm working with. Note that we can nest attributes, like `company.name`, it will transfer us the `name` attribute from the 'company' object attribute.

*Type: String - Required*

**group** when we define a group for a property, we only transfer this attribute when explicitly saying this. When it's omitted, the property is transfered everytime.

*Type: String - Not Required*

**view** when we tell that our property is a view the BaseView'll try to resolve it as a View.

*Type: boolean - Not Required - default false*
