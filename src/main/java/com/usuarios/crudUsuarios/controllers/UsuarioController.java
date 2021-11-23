package com.usuarios.crudUsuarios.controllers;

import com.usuarios.crudUsuarios.models.UsuarioModel;
import com.usuarios.crudUsuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuario(){
        return usuarioService.obtenerUsuario();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    //Se recopila el email por vía query, para luego buscar en la base de datos un email igual al que ingresó
    @GetMapping("/")
    public Optional<UsuarioModel> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        return this.usuarioService.obtenerPorEmail(email);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    //Para este caso, se piden los datos del modelo de usuario por body. Si el id coincide se modifican los datos
    @PutMapping()
    public UsuarioModel modificarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.putAtributosUsuario(usuario);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){

        boolean ok = this.usuarioService.eliminarUsuario(id);
        if(ok){
            return "Se eliminó al usuario con el id: "+ id;
        }else{
            return "No se encontró al usuario con id: "+ id;
        }
    }

    //En este caso se iguala a null la propiedad del usuario especificado por ID
    @DeleteMapping()
    public UsuarioModel quitarPrioridadUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.eliminarPrioridadUsuario(usuario);
    }
}
