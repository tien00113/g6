package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k35dl.g6.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
