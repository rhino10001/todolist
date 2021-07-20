package main.controller;

import main.model.DoingEntity;
import main.model.DoingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private DoingRepository doingRepository;

    @RequestMapping("/")
    public String getStartPage(Model model) {
        Iterable<DoingEntity> doingIterable = doingRepository.findAll();
        ArrayList<DoingEntity> doings = new ArrayList<>();
        for (DoingEntity doing : doingIterable) {
            doings.add(doing);
        }
        model.addAttribute("doings", doings);
        model.addAttribute("doingsCount", doings.size());

        return "index";
    }
}
