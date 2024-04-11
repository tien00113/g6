package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.User;


public interface AddressRepository extends JpaRepository<Address, Long>{

    public Address findByUserAndIsDefault(User user, Boolean isDefault);
}
