package com.example.PerfulandiaSPA.assemblers;

//Importar las clases necesarias para el modelo y controlador
import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Controller.PerfumeController;

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
//La clase PerfumeModelAssembler debe implementar a RepresentationModelAssembler para convertir un objeto de Perfume en EntityModel
public class perfumeModelAssembler implements RepresentationModelAssembler<Perfume, EntityModel<Perfume>> {
    //Anotacion Override para indicar que este metodo implementa un metodo de la interfaz RepresentationModelAssembler
    @Override
    //Metodo para convertir un objeto de Perfume en una EntityModel. Usamos la anotacion NonNull para no aceptar valores nulos,
    //usamos linkTo con el metodo methodOn para crear los enlaces HATEOAS para cada metodo REST del controlador
    public @NonNull EntityModel<Perfume> toModel(Perfume perfume){
        return EntityModel.of(perfume,
            linkTo(methodOn(PerfumeController.class).buscarPerfume(perfume.getId_perfume())).withSelfRel(),
            linkTo(methodOn(PerfumeController.class).listarPerfumes()).withRel("perfume"),
            linkTo(methodOn(PerfumeController.class).eliminarPerfume(perfume.getId_perfume())).withRel("eliminar"),
            linkTo(methodOn(PerfumeController.class).actualizarPerfume(perfume.getId_perfume(),perfume)).withRel("actualizar"));
    }
}
