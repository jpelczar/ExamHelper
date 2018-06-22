package io.jpelczar.examhelper.open

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class OpenController {

    @GetMapping("/")
    fun index(): String = "redirect:/home"

    @GetMapping("/home")
    fun home(): String = "home"



}