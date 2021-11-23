package com.usuarios.crudUsuarios.services;

import com.usuarios.crudUsuarios.models.UsuarioModel;
import com.usuarios.crudUsuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuario(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    //A través de esta función se modifica cualquier atributo del usuario
    public UsuarioModel putAtributosUsuario(UsuarioModel usuario){

        boolean match = usuarioRepository.existsById(usuario.getId());
        if(match){
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public Optional<UsuarioModel> obtenerPorId(long id){
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad){
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public Optional<UsuarioModel> obtenerPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    //Se define un método de eliminación del atributo prioridad del usuario especificado
    public UsuarioModel eliminarPrioridadUsuario(UsuarioModel usuario){
        if(usuarioRepository.existsById(usuario.getId())){
            usuario.setPrioridad(null);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
