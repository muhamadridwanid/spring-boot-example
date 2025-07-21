package com.muhamadridwan.bookservice.repository;

import com.muhamadridwan.bookservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
