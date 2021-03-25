package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Output;

@Repository
public interface OutputRepository extends JpaRepository<Output, Integer> {
}
