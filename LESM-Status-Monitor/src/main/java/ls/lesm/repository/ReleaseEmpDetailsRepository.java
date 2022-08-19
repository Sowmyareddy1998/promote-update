package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ls.lesm.model.ReleaseEmpDetails;
@Repository
public interface ReleaseEmpDetailsRepository extends JpaRepository<ReleaseEmpDetails,Integer> {

}
