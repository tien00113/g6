package com.k35dl.g6.controller.Admin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.models.User;
import com.k35dl.g6.models.User.Role;
import com.k35dl.g6.response.RevenueAndOrdersResponse;
import com.k35dl.g6.service.OrderService;
import com.k35dl.g6.service.UserSerVice;
import com.k35dl.g6.utils.DashBoard;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserSerVice userSerVice;

    @Autowired
    private OrderService orderService;

    @PutMapping("/user/setrole")
    public User adminSetRoleUser(String adminUsername, String targetUsername, Collection<Role> roles) {

        User user = userSerVice.setRolesUser(adminUsername, targetUsername, roles);

        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userSerVice.getAllusers();
    }

    @GetMapping("/profile")
    public User getAdminFromToken(@RequestHeader("Authorization") String jwt) {
        User admin = userSerVice.findUserByJwt(jwt);

        return admin;
    }

    @GetMapping("/dashboard")
    public DashBoard dashBoard() {
        Long revenue = orderService.getTotalRevenue();
        Long totalUser = userSerVice.getTotalUser();
        Long totalOrder = orderService.getTotalOrder();

        return new DashBoard(totalUser, totalOrder, revenue);
    }

    @GetMapping("/dashboard/stats/{days}")
    public RevenueAndOrdersResponse getStatsInLastDays(@PathVariable int days) {
        List<Long> revenue = orderService.getTotalRevenueInLastDays(days);
        List<Long> orders = orderService.getTotalOrderInLastDays(days);
        List<LocalDate> dates = IntStream.range(0, days)
                .mapToObj(i -> LocalDate.now().minusDays(i))
                .collect(Collectors.toList());

        RevenueAndOrdersResponse response = new RevenueAndOrdersResponse();

        response.setOrders(orders);
        response.setRevenue(revenue);
        response.setDates(dates);

        return response;
    }

    @GetMapping("/customer")
    public List<User> getAllCustomer(){
        return userSerVice.getUsersWithRoleUser();
    }
}
