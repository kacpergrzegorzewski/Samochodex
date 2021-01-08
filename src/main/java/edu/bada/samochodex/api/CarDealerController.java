package edu.bada.samochodex.api;

import edu.bada.samochodex.model.CarDealer;
import edu.bada.samochodex.service.CarDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/salony")
public class CarDealerController {

    private final CarDealerService carDealerService;

    @Autowired
    public CarDealerController(CarDealerService carDealerService) {
        this.carDealerService = carDealerService;
    }

    @GetMapping
    public String getCarDealersView(Model model) {
        List<CarDealer> carDealers = carDealerService.getAll();

        model.addAttribute("carDealers", carDealers);

        return "carDealers";
    }

}
