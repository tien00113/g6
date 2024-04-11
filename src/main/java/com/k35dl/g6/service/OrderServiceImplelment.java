package com.k35dl.g6.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.OrderException;
import com.k35dl.g6.models.Address;
import com.k35dl.g6.models.Cart;
import com.k35dl.g6.models.CartItem;
import com.k35dl.g6.models.Order;
import com.k35dl.g6.models.OrderItem;
import com.k35dl.g6.models.User;
import com.k35dl.g6.models.Order.OrderStatus;
import com.k35dl.g6.repository.AddressRepository;
import com.k35dl.g6.repository.OrderItemRepository;
import com.k35dl.g6.repository.OrderRepository;
import com.k35dl.g6.repository.UserRepository;

@Service
public class OrderServiceImplelment implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Order createOrder(User user, Address shipAdress, List<Long> selectedCartItemIds) {

        shipAdress.setUser(user);

        if (user.getAddress().isEmpty()) {
            shipAdress.setIsDefault(true);
        } else if (shipAdress.getIsDefault() == true) {
            Set<Address> addresses = user.getAddress();

            for (Address address : addresses) {
                if (address.getIsDefault() == true) {
                    address.setIsDefault(false);
                    break;
                }
            }

        }
        Address address = addressRepository.save(shipAdress);

        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (Long itemId : selectedCartItemIds) {
            CartItem item = cart.getCartItems().stream().filter(cartItem -> cartItem.getId().equals(itemId)).findFirst()
                    .orElse(null);

            if (item != null) {

                OrderItem orderItem = new OrderItem();

                orderItem.setPrice(item.getPrice());
                orderItem.setPriceSale(item.getPriceSale());
                orderItem.setProduct(item.getProduct());
                orderItem.setUserId(item.getUserId());
                // set size, quantity, topping cho orderitem lấy từ cartitem.

                orderItem.setQuantity(item.getQuantity());
                orderItem.setSizeOption(item.getSizeOption());
                orderItem.setToppingOption(item.getToppingOption());
                OrderItem createOrderItem = orderItemRepository.save(orderItem);

                orderItems.add(createOrderItem);
            }

        }

        Order createdOrder = new Order();

        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(orderItems.stream().mapToInt(OrderItem::getPrice).sum());
        createdOrder.setTotalSalePrice(orderItems.stream().mapToInt(OrderItem::getPriceSale).sum());
        createdOrder.setShippingAddress(address);
        createdOrder.setStatus(OrderStatus.PLACED);
        createdOrder.setCreateAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(createdOrder);

        for (OrderItem item : orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        List<Order> orders = orderRepository.findUserOrders(userId);
        return orders;
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.PLACED);

        return orderRepository.save(order);
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.CONFIRMED);

        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.SHIPPED);

        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.DELIVERED);

        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }

    @Override
    public Order orderNow(User user, Address shipAdress, OrderItem orderItem, String note) {

        shipAdress.setUser(user);

        if (user.getAddress().isEmpty()) {
            shipAdress.setIsDefault(true);
        } else if (shipAdress.getIsDefault() == true) {
            Set<Address> addresses = user.getAddress();

            for (Address address : addresses) {
                if (address.getIsDefault() == true) {
                    address.setIsDefault(false);
                    break;
                }
            }

        }
        
        Address address = addressRepository.save(shipAdress);

        user.getAddress().add(address);
        userRepository.save(user);

        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        order.setOrderItems(orderItems);
        order.setShippingAddress(address);
        order.setUser(user);
        order.setTotalPrice(orderItem.getPrice());
        order.setTotalSalePrice(orderItem.getPriceSale());
        order.setNote(note);
        order.setStatus(OrderStatus.PLACED);
        order.setCreateAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        orderItem.setOrder(savedOrder);
        orderItemRepository.save(orderItem);

        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findById(orderId);

        if (opt.isEmpty()) {
            throw new OrderException("Không tìm thấy đơn hàng có id " + orderId);
        }

        return opt.get();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

        Order order = findOrderById(orderId);

        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getALlOrders() throws OrderException {
        List<Order> orders = orderRepository.findAll();

        return orders;
    }

    @Override
    public Order findOrderByOrderId(String orderId) throws OrderException {
        Order order = orderRepository.findByOrderId(orderId);

        return order;
    }

}
