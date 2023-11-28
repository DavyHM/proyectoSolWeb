package proyecto.demo.Model.dao;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.Usuario;


public interface IUsuarioDAO extends CrudRepository<Usuario,Long> {
    public Usuario findByUsername(String username);
}

