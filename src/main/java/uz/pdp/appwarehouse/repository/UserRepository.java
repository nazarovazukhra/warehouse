package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
