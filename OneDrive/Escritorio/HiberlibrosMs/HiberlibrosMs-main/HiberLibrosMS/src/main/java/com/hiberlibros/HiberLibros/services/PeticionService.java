package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Peticion;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import com.hiberlibros.HiberLibros.interfaces.IPeticionService;
import com.hiberlibros.HiberLibros.repositories.PeticionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioLibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;

@Service
public class PeticionService implements IPeticionService {

    @Autowired
    private PeticionRepository repoPeticion;

    @Autowired
    private IUsuarioLibroService ulService;

    

    @Override
    public List<Peticion> consultaTodasPeticiones() {
        return repoPeticion.findAll();
    }
    
    @Override
    public Peticion consultarPeticionId(Integer id) {
        return repoPeticion.findById(id).get();
    }

    @Override
    public void insertaPeticion(Peticion p, Integer id_ul, Usuario u) { //guarda la petición y obtiene aquí los objetos UL y Usuario
        p.setIdUsuarioLibro(ulService.encontrarId(id_ul));
        p.setIdUsuarioSolicitante(u);
        p.setAceptacion(false);
        p.setPendienteTratar(true);

        repoPeticion.save(p);
    }

    @Override
    public void insertaModificaPeticion(Peticion p) {

        repoPeticion.save(p);
    }

    @Override
    public void eliminaPeticion(Peticion p) {
        repoPeticion.deleteById(p.getId());
    }

    @Override
    public void eliminarId(Integer id) {
        repoPeticion.deleteById(id);;
    }

    @Override
    public void aceptarPeticion(Peticion p) {
        p.setAceptacion(Boolean.TRUE);
        p.setPendienteTratar(Boolean.FALSE);
        repoPeticion.save(p);
    }

    @Override
    public void rechazarPeticion(Integer id) {
        Peticion p = repoPeticion.findById(id).get();
        p.setAceptacion(Boolean.FALSE);
        p.setPendienteTratar(Boolean.FALSE);
        repoPeticion.save(p);
    }

    @Override
    public List<Peticion> consultarPeticionesPendientes(Usuario u) {
        return repoPeticion.findByPendienteTratar(Boolean.TRUE).stream().filter(x -> x.getIdUsuarioSolicitante().equals(u.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Peticion> consultarPeticionesAceptadas(Usuario u) {
        return repoPeticion.findByAceptacion(Boolean.TRUE).stream().filter(x -> x.getIdUsuarioSolicitante().equals(u.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Peticion> consultarPeticionesRechazadas(Usuario u) {
        return repoPeticion.findByAceptacion(Boolean.FALSE).stream().filter(x -> x.getIdUsuarioSolicitante().equals(u.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Peticion> consutarPeticionesUsuarioPendientes(Usuario u) {
        return repoPeticion.findByPendienteTratarAndIdUsuarioSolicitante(Boolean.TRUE, u);
    }

    @Override
    public List<Peticion> consultarPeticonesRecibidas(Usuario u) { //Jesús
        List<Peticion> p = new ArrayList<>();
        List<UsuarioLibro> ul = ulService.buscarUsuario(u);//busca la lista de libros de un usuario
        ul.forEach(x -> {
            List<Peticion> pAux = repoPeticion.findByIdUsuarioLibroAndPendienteTratar(x, Boolean.TRUE); //busca por UsuarioLibro y que este pendiente de tratar
            pAux.forEach(y -> {
                p.add(y);//lo va almacenando hasta tener todos. 
            });
        });
        return p;
    }

    @Override
    public void borrarPorUsuarioSolicitante(Usuario u) {
        repoPeticion.deleteByIdUsuarioSolicitante(u);        
    }

    @Override
    public void borrarPorUsuarioLibro(UsuarioLibro ul) {
        repoPeticion.deleteByIdUsuarioLibro(ul);
    }

}
