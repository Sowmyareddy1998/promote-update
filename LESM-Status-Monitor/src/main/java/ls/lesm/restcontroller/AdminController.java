package ls.lesm.restcontroller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.exception.RoleAreadyExistException;
import ls.lesm.model.Role;
import ls.lesm.model.User;
import ls.lesm.model.UserRole;
import ls.lesm.repository.RoleRepository;
import ls.lesm.repository.UserRepository;
import ls.lesm.service.impl.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	private static final Logger LOG=LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRopository;
	
	//create User
	@PostMapping("/sign-up")
	public User createUser(@RequestBody User user) throws Exception {
		LOG.info("Enterd into createUser Method");

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		LOG.debug("Encrypted password");
		Set<UserRole> userRoleSet=new HashSet<>();

		Role role=new Role();         //default role "User"
		role.setRoleId(11);
		role.setRoleName("USER");
		

		user.setRoleName(role.getRoleName());

		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		LOG.debug("Assigned Default role to user to USER");
		userRoleSet.add(userRole);
		return this.userService.createUser(user, userRoleSet);
	}
	
	
	@PostMapping("/create-roles")
	public ResponseEntity<?> createRoles(@RequestBody Role role) {
	
		Role local1=this.roleRopository.findByRoleName(role.getRoleName());
		Role local2=this.roleRopository.findByRoleId(role.getRoleId());
		if(local1!=null)
			throw new RoleAreadyExistException("111","This Role Name Is Already Exist");
		if(local2!=null)
			throw new RoleAreadyExistException("222", "This Role ID Is Already Exist");
		else 
			role.setRoleName(role.getRoleName().toUpperCase());
			  this.roleRopository.save(role);
	
		return new ResponseEntity<Role>(HttpStatus.ACCEPTED);
		}

}
