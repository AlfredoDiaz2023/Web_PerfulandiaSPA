package com.example.PerfulandiaSPA.assemblers;

//Importar las clases necesarias para el modelo y controlador
import com.example.PerfulandiaSPA.Model.Usuario;
import com.example.PerfulandiaSPA.Controller.UsuarioController;

//Importar las clases static para crear los enlaces HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//Importar la clase EntityModel para usar HATEOAS
import org.springframework.hateoas.EntityModel;

//Importar la interfaz RepresentationModelAssembler para crear el ensamblador de PerfumeModelAssambler
import org.springframework.hateoas.server.RepresentationModelAssembler;

//Importar los stereotipos necesarios para el ensamblador
import org.springframework.stereotype.Component;

//Importar la anotacion NonNull para indicar que el metodo no acepta valores nulos
import org.springframework.lang.NonNull;

//Agregar la anotacion Component para indicar que nuestra clase PerfumeModelAssembler es un componente Spring
@Component

public class usuarioModelAssembler implements RepresentationModelAssembler <Usuario, EntityModel<Usuario>>{
    @Override
    public@NonNull EntityModel<Usuario> toModel(Usuario u){
        return EntityModel.of(u, 
        linkTo(methodOn(UsuarioController.class).registrar(null)).withSelfRel(),
        linkTo(methodOn(UsuarioController.class).login(u)).withRel("login"));
    }    
}
