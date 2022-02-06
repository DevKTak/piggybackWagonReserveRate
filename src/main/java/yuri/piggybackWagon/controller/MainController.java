package yuri.piggybackWagon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yuri.piggybackWagon.service.MainService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Map<String, Object>> findReserveDateBykyungAndJung(@RequestParam int kyung, @RequestParam int jung) {
        return mainService.findReserveDateBykyungAndJung(kyung, jung);
    }
}
