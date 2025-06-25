package com.example.PerfulandiaSPA.assemblers;

//Importar la clase Static para crear enlaces HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Importar la clase EntityModel para usar HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

//Importar la anotacion NonNull para indicar que el metodo no acepta valores nulos
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

//Importar las clases necesarias para el modelo y controlador
import com.example.PerfulandiaSPA.Controller.CarritoController;
import com.example.PerfulandiaSPA.Model.Perfume;

@Component
public class carritoModelAssembler implements RepresentationModelAssembler<Perfume, EntityModel<Perfume>> {

    @Override
    public @NonNull EntityModel<Perfume> toModel(Perfume perfume){
        return EntityModel.of(perfume,
            linkTo(methodOn(CarritoController.class).verCarrito()).withRel("carrito"),
            linkTo(methodOn(CarritoController.class).eliminarPerfume(perfume.getId_perfume())).withRel("eliminar"));
    }
}
