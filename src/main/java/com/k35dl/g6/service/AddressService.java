package com.k35dl.g6.service;

import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.User;

public interface AddressService {
    public Address createAddress(User user,Address address);
    public Address updateAddress(Address address, Long addressId);
}
