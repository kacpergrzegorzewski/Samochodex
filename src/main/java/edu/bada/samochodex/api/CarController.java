package edu.bada.samochodex.api;

import edu.bada.samochodex.model.*;
import edu.bada.samochodex.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final CarBrandService carBrandService;
    private final CarModelService carModelService;
    private final EngineService engineService;
    private final EquipmentVersionService equipmentVersionService;
    private final OrderService orderService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    @Autowired
    public CarController(CarService carService, CarDealerService carDealerService, CarBrandService carBrandService,
                         CarModelService carModelService, EngineService engineService,
                         EquipmentVersionService equipmentVersionService, OrderService orderService,
                         EmployeeService employeeService, ClientService clientService) {
        this.carService = carService;
        this.carDealerService = carDealerService;
        this.carBrandService = carBrandService;
        this.carModelService = carModelService;
        this.engineService = engineService;
        this.equipmentVersionService = equipmentVersionService;
        this.orderService = orderService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showCarPage(Model model) {
        List<Car> cars = carService.getAllForSale();

        model.addAttribute("cars", cars);

        return "cars/cars";
    }

    @GetMapping("/{id}")
    public String showCarDetails(@PathVariable("id") Long id, Model model) {
        Car car = carService.getById(id);

        model.addAttribute("car", car);

        return "cars/car_details";
    }

    @PostMapping("/zamow")
    public String orderCar(@ModelAttribute("car") Car car) {
        Order order = new Order();
        Random random = new Random();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Randomly chosen employee
        Employee employee = employeeService.getAll().get(random.nextInt(employeeService.getAll().size()));

        // Client who is currently logged-in
        Client client = clientService.getByEmail(authentication.getName());

        // Potrzebuję tego, żeby zebrać wszystkie atrytuby obowiązkowe samochodu w zamówieniu,
        // bo przez forma się nie przesyłają
        Car orderedCar = carService.getById(car.getId());

        order.setData(new Date(System.currentTimeMillis()));
        order.setCar(orderedCar);
        order.setCarDealer(orderedCar.getCarDealer());
        order.setEmployee(employee);
        order.setClient(client);

        // Klikanie "zamów teraz" sprawia, że samochód z zamówienia nie jest już na sprzedaż
        orderedCar.setNaSprzedaz(false);
        carService.save(orderedCar);

        orderService.save(order);

        return "redirect:/samochody";
    }

    /* ------ EMPLOYEE, ADMIN ------- */

    @GetMapping("/dodaj")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public String addCarPage(Model model) {
        Car car = new Car();
        CarModel carModel = new CarModel();
        List<CarDealer> carDealers = carDealerService.getAll();
        List<Engine> engines = engineService.getAll();
        List<EquipmentVersion> equipmentVersions = equipmentVersionService.getAll();
        List<CarBrand> carBrands = carBrandService.getAll();

        model.addAttribute("car", car);
        model.addAttribute("carBrands", carBrands);
        model.addAttribute("carModel", carModel);
        model.addAttribute("carDealers", carDealers);
        model.addAttribute("engines", engines);
        model.addAttribute("equipmentVersions", equipmentVersions);

        return "cars/add_car";
    }

    @PostMapping("/dodaj/zapisz")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    public String saveCar(Model model, Car car, CarModel carModel) {
        model.addAttribute("car", car);
        model.addAttribute("carModel", carModel);

        carModel.setCarBrand(car.getCarModel().getCarBrand());
        carModelService.save(carModel);

        car.setCarModel(carModel);
        carService.save(car);

        return "redirect:/samochody";
    }
}
