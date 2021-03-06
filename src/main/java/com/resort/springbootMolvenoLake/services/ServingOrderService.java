package com.resort.springbootMolvenoLake.services;

import com.resort.springbootMolvenoLake.models.MenuItem;
import com.resort.springbootMolvenoLake.models.Order;
import com.resort.springbootMolvenoLake.models.ServingOrder;
import com.resort.springbootMolvenoLake.repositories.ServingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServingOrderService {
    @Autowired
    private ServingOrderRepository servingOrderRepository;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrderService orderService;

    public int create(ServingOrder servingOrder) {
        ServingOrder created = servingOrderRepository.save(servingOrder);
        // The returned value of the repository doesn't contain the unit name
        // So get it with the unitService
        created.setMenuItem(
                menuItemService.read(created.getMenuItem().getId())
        );
        return created.getId();
    }

    public List<ServingOrder> all() {
        Iterable<ServingOrder> iterable = servingOrderRepository.findAll();
        List<ServingOrder> servingsOrders = new ArrayList<>();
        iterable.forEach(servingsOrders::add);
        return servingsOrders;
    }

    public List<ServingOrder> allFoodItems() {
        Iterable<ServingOrder> iterable = servingOrderRepository.findAll();
        List<ServingOrder> foodOrders = new ArrayList<>();
        for (ServingOrder order : this.all()) {
            MenuItem item = order.getMenuItem();
            if (item.getCategory().getName().equals("Food")) {
                foodOrders.add(order);
            }
        }
        return foodOrders;
    }

    public List<ServingOrder> allDrinkItems() {
        Iterable<ServingOrder> iterable = servingOrderRepository.findAll();
        List<ServingOrder> drinkOrders = new ArrayList<>();
        for (ServingOrder order : this.all()) {
            MenuItem item = order.getMenuItem();
            if (item.getCategory().getName().equals("Drinks")) {
                drinkOrders.add(order);
            }
        }
        return drinkOrders;
    }

    public ServingOrder read(final int id) {
        Optional<ServingOrder> serving = servingOrderRepository.findById(id);
        if (serving.isPresent()) {
            return serving.get();
        }
        return null;
    }

    public ServingOrder update(int id, ServingOrder servingOrder) {
        Optional<ServingOrder> oldServing = servingOrderRepository.findById(id);
        if (oldServing.isPresent()) {
            if (servingOrder.getMenuItem() != null) {
                oldServing.get().setMenuItem(servingOrder.getMenuItem());
            }
            if (servingOrder.getNumberOfMenuItems() != 0) {
                oldServing.get().setNumberOfMenuItems(servingOrder.getNumberOfMenuItems());
            }
        }

        ServingOrder saved = servingOrderRepository.save(oldServing.get());
        MenuItem menuItem = menuItemService.read(saved.getMenuItem().getId());
        System.out.println("New menu item:" + menuItem);
        saved.setMenuItem(menuItem);

        return saved;
    }

    public void delete(final int id) {
        servingOrderRepository.deleteById(id);
    }

    public List<ServingOrder> allForOrder(final int id) {
        Order order = orderService.read(id);
        if (order != null) {
            return order.getServingOrders();
        } else {
            return new ArrayList<>();
        }
    }

    public List<ServingOrder> allFoodForOrder(final int id) {
        Order order = orderService.read(id);
        if (order != null) {
            List<ServingOrder> foodOrders = new ArrayList<>();
            for (ServingOrder o : order.getServingOrders()) {
                MenuItem item = o.getMenuItem();
                if (item.getCategory().getName().equals("Food")) {
                    foodOrders.add(o);
                }
            }
            return foodOrders;
        } else {
            return new ArrayList<>();
        }
    }

    public List<ServingOrder> allDrinksForOrder(final int id) {
        Order order = orderService.read(id);
        if (order != null) {
            List<ServingOrder> drinksOrders = new ArrayList<>();
            for (ServingOrder o : order.getServingOrders()) {
                MenuItem item = o.getMenuItem();
                if (item.getCategory().getName().equals("Drinks")) {
                    drinksOrders.add(o);
                }
            }
            return drinksOrders;
        } else {
            return new ArrayList<>();
        }
    }

    public ServingOrder createForOrder(int id, ServingOrder servingOrder) {
        this.create(servingOrder);

        Order order = orderService.read(id);
        if (order != null) {
            order.getServingOrders().add(servingOrder);
            orderService.update(id, order);

            int menuItemId = servingOrder.getMenuItem().getId();
            servingOrder.setMenuItem(
                    menuItemService.read(menuItemId)
            );
            return servingOrder;
        } else {
            return null;
        }
    }
}
