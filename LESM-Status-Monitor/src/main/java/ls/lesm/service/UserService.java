package ls.lesm.service;

import java.util.Set;

import ls.lesm.model.User;
import ls.lesm.model.UserRole;

public interface UserService {
	
	//creating a new user with roles
	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	
	

}
