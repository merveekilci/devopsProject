package com.sau.myapp2.Repository;

import com.sau.myapp2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
