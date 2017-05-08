package hibernate_6_5;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.ejb.EJB;



public class ManageUser {
	
    private User user = new User();	
	//private User user;
    private List<User> UserList = new ArrayList<>();  

    private String Username;
    private String Password;
    @EJB
    private EjbUser ejbUser;
    

    private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();

 
   public List<User> getUserList() {
        UserList = ejbUser.findUsers();
      /*  checked.clear();
        for (Iterator<User> iter = UserList.iterator(); iter.hasNext(); ) {
        	User element = iter.next();
        	checked.put(element.getId(),false);
        }*/

        return UserList;
    }
 
   public String viewUser(){
        return "listUser.xhtml";
    }
   //employeeController.addNewEmployee
   public String saveUser(User usr)
   {
	  ejbUser.addNew(usr);
	   return "listUser.xhtml";
   }
   
   public String save2User(){
   System.err.println("username to be set " + this.Username);
   System.err.println("password to be set " + this.Password);
   user.setUsername(this.Username);
   user.setPassword(this.Password);
   ejbUser.addNew(user);
   return "listUser.xhtml";
   }
   
   public String deleteUser(){
	   System.err.println("mphka deleteUser");
      // UserList = ejbUser.findUsers();
      // checked.clear();

       
       for (Map.Entry<Integer, Boolean> entry : checked.entrySet())
       {
    	   System.out.println(entry.getKey() + ", " + entry.getValue());
    	   if(entry.getValue().equals(true))
    		   ejbUser.deleteU(entry.getKey());
           //System.out.println(entry.getKey() + "/" + entry.getValue());
       }
	   
	  // ejbUser.deleteU(Username);
	   return "listUser.xhtml";
   }
    

	public EjbUser getEjbUser() {
	return ejbUser;
}

public void setEjbUser(EjbUser ejbUser) {
	this.ejbUser = ejbUser;
}

	public User getUser() {
		 System.err.println("mphka getUser");
		return user;
	}

	public void setUser(User user) {
		System.err.println("mphka setUser");
		this.user = user;
	}

	public void setUserList(List<User> userList) {
		this.UserList = userList;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(final String username) {
		System.err.println("setUsername final = " +username);
		System.err.println("setUsername previous = " +this.Username);
		this.Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(final String password) {
		System.err.println("setPassword final = " +password);
		System.err.println("setPassword previous = " +this.Password);
		this.Password = password;
	}

	public Map<Integer, Boolean> getChecked() {
		return checked;
	}


    
    
}
