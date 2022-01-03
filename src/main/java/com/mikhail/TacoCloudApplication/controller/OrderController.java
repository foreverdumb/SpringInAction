package com.mikhail.TacoCloudApplication.controller;

import com.mikhail.TacoCloudApplication.database.repository.TacoOrderRepository;
import com.mikhail.TacoCloudApplication.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private final TacoOrderRepository tacoOrderRepository;

    public OrderController(TacoOrderRepository tacoOrderRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderFormPage";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderFormPage";
        }
        tacoOrderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
