package org.top.springwithdbexample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 'findAllByUsername' - название должно быть именно таким
    List<User> findAllByUsername(String username);
    List<User> findAllByUsernameContaining(String pattern);
}
