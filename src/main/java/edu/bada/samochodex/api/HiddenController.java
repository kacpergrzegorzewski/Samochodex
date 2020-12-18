package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Hidden;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hidden")
public class HiddenController {

    private static List<Hidden> hiddenList = Arrays.asList(
            new Hidden(1, "Zbyszek kocha Monikę"),
            new Hidden(2, "Całkiem fajny ten projekt")
    );

    @GetMapping("/{hiddenId}")
    public Hidden getHidden(@PathVariable("hiddenId") Integer hiddenId) {
        return hiddenList.stream()
                .filter(hidden -> hiddenId.equals(hidden.getHiddenId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Hidden sentence %d does not exist", hiddenId)));
    }

    @GetMapping
    public List<Hidden> getAllHidden() {
        return hiddenList;
    }

    @PostMapping
    public void registerNewHidden(@RequestBody Hidden hidden) {
        System.out.println(hidden);
    }

    @DeleteMapping("{hiddenId}")
    public void deleteHidden(@PathVariable("hiddenId") Integer hiddenId) {
        System.out.println(hiddenId);
    }

    @PutMapping("{hiddenId}")
    public void updateStudent(@PathVariable("hiddenId") Integer hiddenId, @RequestBody Hidden hidden) {
        System.out.printf("%s %s%n", hiddenId, hidden);
    }
}
