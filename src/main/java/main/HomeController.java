package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // http://localhost:8888/ �� �����ϸ� �� �޼��尡 ����˴ϴ�.
    @GetMapping("/")
    public String redirectToMain() {
        // "redirect:/main"�� ����ڸ� /main ��η� �ٽ� ������� ���Դϴ�.
        return "redirect:/main";
    }
}