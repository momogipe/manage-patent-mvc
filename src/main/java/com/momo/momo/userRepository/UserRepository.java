package com.momo.momo.userRepository;

import com.momo.momo.EntitiesUser.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public  Long countByid(Integer id);
    boolean existsById(Long id);

    Optional<User> findVoyageByFirstname(String nom);

}
