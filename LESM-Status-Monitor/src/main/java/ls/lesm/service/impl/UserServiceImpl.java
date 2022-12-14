package ls.lesm.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ls.lesm.exception.UserAlreadinExistException;
import ls.lesm.model.User;
import ls.lesm.model.UserRole;
import ls.lesm.repository.RoleRepository;
import ls.lesm.repository.UserRepository;
import ls.lesm.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User already exist with this username!!");
			throw new UserAlreadinExistException("user already present with this username","present");
			//create user
		}
		else {

			for(UserRole ur: userRole) {
				roleRepository.save(ur.getRole());//role save
				System.out.println(ur);
			}

			user.getUserRole().addAll(userRole);//associating roles to user
			local=this.userRepository.save(user);
		}
		return local;
	}

}
