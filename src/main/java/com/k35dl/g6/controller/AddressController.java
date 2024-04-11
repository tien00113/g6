package com.k35dl.g6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.User;
import com.k35dl.g6.service.AddressService;
import com.k35dl.g6.service.UserSerVice;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserSerVice userSerVice;

    @PostMapping("/api/address/create")
    public ResponseEntity<Address> createAddressUser (@RequestHeader("Authorization") String jwt, @RequestBody Address address){
        User user = userSerVice.findUserByJwt(jwt);

        Address createAddress = addressService.createAddress(user, address);

        return new ResponseEntity<>(createAddress, HttpStatus.ACCEPTED);
    }
}
