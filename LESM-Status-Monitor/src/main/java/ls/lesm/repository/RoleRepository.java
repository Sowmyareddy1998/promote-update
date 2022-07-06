package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {



	Role findByRoleName(String roleName);

	Role findByRoleId(Integer roleId);

	void deleteByRoleName(String roleName);


}
