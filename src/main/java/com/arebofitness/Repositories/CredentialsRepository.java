/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.Models.CredentialsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author fermin
 */
public interface CredentialsRepository extends JpaRepository<CredentialsEntity, Long>{
    @Query(value = "select * from credentials where email=:email and password=:password",nativeQuery = true)
    Optional<CredentialsEntity> findByUsernameAndPassword(String email, String password);
}
