package com.k35dl.g6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.User;
import com.k35dl.g6.repository.AddressRepository;
import com.k35dl.g6.repository.UserRepository;

@Service
public class AddressServiceImplement implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address createAddress(User user, Address address) {
        Address newAddress = new Address();

        newAddress.setUser(user);
        newAddress.setCity(address.getCity());
        newAddress.setDistrict(address.getDistrict());
        newAddress.setWard(address.getWard());
        newAddress.setStreet(address.getStreet());
        newAddress.setPhoneNumber(address.getPhoneNumber());
        newAddress.setRecipientName(address.getRecipientName());

        if (user.getAddress().isEmpty()) {
            newAddress.setIsDefault(true);
        } else if (address.getIsDefault() == true) {
            Address address2 = addressRepository.findByUserAndIsDefault(user, true);
            address2.setIsDefault(false);
            newAddress.setIsDefault(true);
        } else {
            newAddress.setIsDefault(address.getIsDefault());
        }
        userRepository.save(user);
        Address savedAddress = addressRepository.save(newAddress);

        user.getAddress().add(savedAddress);

        return savedAddress;
    }

    @Override
    public Address updateAddress(Address address, Long addressId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAddress'");
    }

}
