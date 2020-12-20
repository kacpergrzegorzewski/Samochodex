package edu.bada.samochodex.api;

import edu.bada.samochodex.model.Hidden;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hidden")
public class HiddenController {

    private static final List<Hidden> hiddenList = Arrays.asList(
            new Hidden(1, "Zbyszek kocha Monikę"),
            new Hidden(2, "Całkiem fajny ten projekt")
    );

    @GetMapping("/{hiddenId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public Hidden getHidden(@PathVariable("hiddenId") Integer hiddenId) {
        return hiddenList.stream()
                .filter(hidden -> hiddenId.equals(hidden.getHiddenId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Hidden sentence %d does not exist", hiddenId)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public List<Hidden> getAllHidden() {
        return hiddenList;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hidden:write')")
    public void registerNewHidden(@RequestBody Hidden hidden) {
        System.out.println(hidden);
    }

    @DeleteMapping("{hiddenId}")
    @PreAuthorize("hasAuthority('hidden:write')")
    public void deleteHidden(@PathVariable("hiddenId") Integer hiddenId) {
        System.out.println(hiddenId);
    }

    @PutMapping("{hiddenId}")
    @PreAuthorize("hasAuthority('hidden:write')")
    public void updateStudent(@PathVariable("hiddenId") Integer hiddenId, @RequestBody Hidden hidden) {
        System.out.printf("%s %s%n", hiddenId, hidden);
    }
}
