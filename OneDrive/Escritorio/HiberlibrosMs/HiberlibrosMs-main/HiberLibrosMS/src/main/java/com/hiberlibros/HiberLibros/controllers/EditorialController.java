package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Editorial;
import com.hiberlibros.HiberLibros.interfaces.IEditorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping
public class EditorialController {

    @Autowired
    private IEditorialService serviceEditorial;

    @RequestMapping(value = "/editoriales", method = {RequestMethod.POST, RequestMethod.GET})
    public String editoriales(Model m, Editorial editorial) {
        if (editorial.getId() == null) {
            editorial = new Editorial();
        } else {
            editorial = serviceEditorial.consultaPorIdEditorial(editorial.getId());
        }
        m.addAttribute("editorial", editorial);
        m.addAttribute("editoriales", serviceEditorial.consultaTodas());
        return "/editoriales/editoriales";
    }

    @PostMapping("alta")
    public String editorialesAlta(Model m, Editorial ed) {
        List<Editorial> editoriales = serviceEditorial.consultaPorNombreEditorial(ed);
        String errMensaje = null;
        if (editoriales.size() > 0) {
            errMensaje = "editorial existente";
        } else {
            serviceEditorial.altaModificacionEditorial(ed);
            m.addAttribute("editorial", serviceEditorial.consultaPorIdEditorial(ed.getId()));
        }
        m.addAttribute("errMensaje", errMensaje);
        return "redirect:/editoriales/editoriales";
    }

    @PostMapping("baja")
    public String editorialesBaja(Model m, Integer id) {
        if (serviceEditorial.bajaEditorial(id)) {
            m.addAttribute("borrado", "Borrado con éxito");
        } else {
            m.addAttribute("borrado", "Error, no es posible borrar este autor");
        }
        
        return "redirect:/editoriales/editoriales";
    }

    @PostMapping("/editoriales/modificacion")
    public String editorialesModificacion(Model m, Editorial ed) {
        serviceEditorial.altaModificacionEditorial(ed);
        return "redirect:/editoriales/listarAdmin";
    }

    @PostMapping("consulta")
    public String editorialesConsulta(Model m, String id) {
        m.addAttribute("editorial", serviceEditorial.consultaPorIdEditorial(Integer.parseInt(id)));

        return "forward:/editoriales/editoriales";
    }
    @GetMapping("/editoriales/listarAdmin")
    public String listaAdmin(Model m, String borrado) {
        m.addAttribute("editoriales", serviceEditorial.consultaTodas());
        return "administrador/editoriales";
    }
    @GetMapping("/editoriales/editar")
    @ResponseBody
    public Editorial editarEdit(Integer id) {
        Editorial edit = serviceEditorial.consultaPorIdEditorial(id);
        return edit;
    }
}