package edu.bada.samochodex.api;

import edu.bada.samochodex.model.*;
import edu.bada.samochodex.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/samochody")
public class CarController {

    private final CarService carService;
    private final CarDealerService carDealerService;
    private final CarModelService carModelService;
    private final CarBrandService carBrandService;
    private final EngineService engineService;
    private final EquipmentVersionService equipmentVersionService;

    @Autowired
    public CarController(CarService carService, CarDealerService carDealerService, CarModelService carModelService,
                         CarBrandService carBrandService, EngineService engineService, EquipmentVersionService equipmentVersionService) {
        this.carService = carService;
        this.carDealerService = carDealerService;
        this.carModelService = carModelService;
        this.carBrandService = carBrandService;
        this.engineService = engineService;
        this.equipmentVersionService = equipmentVersionService;
    }

    @GetMapping
    public String showCarPage(Model model) {
        List<Car> cars = carService.getAll();
        List<CarDealer> carDealers = carDealerService.getAll();
        List<CarModel> carModels = carModelService.getAll();
        List<CarBrand> carBrands = carBrandService.getAll();
        List<Engine> engines = engineService.getAll();
        List<EquipmentVersion> equipmentVersions = equipmentVersionService.getAll();

        model.addAttribute("cars", cars);
        model.addAttribute("carDealers", carDealers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("carBrands", carBrands);
        model.addAttribute("engines", engines);
        model.addAttribute("equipmentVersions", equipmentVersions);

        return "cars/cars";
    }
}
