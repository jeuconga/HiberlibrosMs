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
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private IEditorialService serviceEditorial;

    @RequestMapping( method = {RequestMethod.POST, RequestMethod.GET})
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

    @PostMapping("/alta")
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

    @GetMapping("/eliminarEditorial")
    public String editorialesBaja(Integer id) {
        String borrado="";
        if (serviceEditorial.bajaEditorial(id)) {
            borrado="Editorial borrada";
        } else {
            borrado="Error, no es posible borrar esta editorial";
        }
        
        return "redirect:/editoriales/listarAdmin?borrado="+borrado;
    }

    @PostMapping("/modificacion")
    public String editorialesModificacion(Model m, Editorial ed) {
        serviceEditorial.altaModificacionEditorial(ed);
        return "redirect:/editoriales/listarAdmin";
    }

    @PostMapping("consulta")
    public String editorialesConsulta(Model m, String id) {
        m.addAttribute("editorial", serviceEditorial.consultaPorIdEditorial(Integer.parseInt(id)));

        return "forward:/editoriales/editoriales";
    }
    @GetMapping("/listarAdmin")
    public String listaAdmin(Model m, String borrado) {
        m.addAttribute("editoriales", serviceEditorial.consultaTodas());
        if(borrado!=null){
            m.addAttribute("borrado", borrado);
        }
        return "administrador/editoriales";
    }
    @GetMapping("/editar")
    @ResponseBody
    public Editorial editarEdit(Integer id) {
        Editorial edit = serviceEditorial.consultaPorIdEditorial(id);
        return edit;
    }
}