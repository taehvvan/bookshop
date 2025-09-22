package join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/join")
public class JoinController {

    @Autowired
    private JoinService joinService;

    // GET ��û�� ó���ϵ��� ���
    @GetMapping("/joinform")
    public String joinForm() {
        return "joinForm"; 
    }

    // POST ��û�� ó���ϵ��� ����ϰ�, URL�� �� ��Ȯ�ϰ� ����
    @PostMapping("/register")
    public String register(UserVO user, Model model) {
        int result = joinService.register(user);
        model.addAttribute("msg", result > 0 ? "ȸ������ ����!" : "ȸ������ ����");
        return "joinResult"; 
    }
}