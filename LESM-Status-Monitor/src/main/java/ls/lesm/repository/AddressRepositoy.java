package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.Address;

public interface AddressRepositoy extends JpaRepository<Address, Integer> {

}
