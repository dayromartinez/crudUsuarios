package com.usuarios.crudUsuarios.repositories;

import com.usuarios.crudUsuarios.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UsuarioRepository  extends CrudRepository<UsuarioModel, Long> {

    public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);

    //Se define un método de búsqueda de usuarios adicional, en este caso por email.
    public abstract Optional<UsuarioModel> findByEmail(String email);
}
