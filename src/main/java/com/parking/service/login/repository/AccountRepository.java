package com.parking.service.login.repository;

import com.parking.service.login.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Query(value = "select * from account where username =:username", nativeQuery = true)
    Optional<AccountEntity> getAccountByUsername(@Param("username") String username);
}
