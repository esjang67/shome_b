package com.esjang.sthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
