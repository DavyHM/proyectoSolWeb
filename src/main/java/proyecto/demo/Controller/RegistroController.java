package proyecto.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proyecto.demo.Model.entidad.Usuario;
import proyecto.demo.Model.service.UserService;

@Controller
public class RegistroController {

    @Autowired
    UserService userService;
    
    @RequestMapping("/registrar")
    public String iniciarRegistro(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "login/register";
    }

    @RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST)
    public String guardarUsuario(Usuario usuario){
        
        userService.guardarUsuario(usuario);
        return "redirect:/autenticar";
    }
}
