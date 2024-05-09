import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("${api.basePath}")
class LoginController {
	@GetMapping("/login")
	String login() {
		return "login";
	}
}