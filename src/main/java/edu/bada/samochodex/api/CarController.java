package edu.bada.samochodex.api;

import edu.bada.samochodex.model.*;
import edu.bada.samochodex.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/samochody")
public class CarController {

    private final CarService carService;
    private final CarDealerService carDealerService;

    private final EngineService engineService;
    private final EquipmentVersionService equipmentVersionService;
    private final OrderService orderService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    @Autowired
    public CarController(CarService carService, CarDealerService carDealerService, EngineService engineService,
                         EquipmentVersionService equipmentVersionService, OrderService orderService,
                         EmployeeService employeeService, ClientService clientService) {
        this.carService = carService;
        this.carDealerService = carDealerService;
        this.engineService = engineService;
        this.equipmentVersionService = equipmentVersionService;
        this.orderService = orderService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showCarPage(Model model) {
        List<Car> cars = carService.getAll();

        model.addAttribute("cars", cars);

        return "cars/cars";
    }

    @GetMapping("/{id}")
    public String showCarDetails(@PathVariable("id") Long id, Model model) {
        Car car = carService.getById(id);

        model.addAttribute("car", car);

        return "cars/car_details";
    }

    @PostMapping("/zapisz")
    public String savePost(@ModelAttribute("car") Car orderedCar) {
        Order order = new Order();
        Random random = new Random();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Randomly chosen employee
        Employee employee = employeeService.getAll().get(random.nextInt(employeeService.getAll().size()));

        // Client who is currently logged-in
        Client client = clientService.getByEmail(authentication.getName());

        order.setData(new Date(System.currentTimeMillis()));
        order.setCar(orderedCar);
        order.setCarDealer(orderedCar.getCarDealer());
        order.setEmployee(employee);
        order.setClient(client);

        orderService.save(order);

        return "redirect:/samochody";
    }

    @GetMapping("/zamów/{id}")
    public String showCarOrderForm(@PathVariable("id") long id, Model model) {
        Car car = carService.getById(id);
        List<Engine> engines = engineService.getAll();
        List<EquipmentVersion> equipmentVersions = equipmentVersionService.getAll();
        List<CarDealer> carDealers = carDealerService.getAll();

        model.addAttribute("car", car);
        model.addAttribute("engines", engines);
        model.addAttribute("equipmentVersions", equipmentVersions);
        model.addAttribute("carDealers", carDealers);

        return "cars/order_car";
    }
}
