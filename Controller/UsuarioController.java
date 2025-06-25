package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Usuario;
import com.example.PerfulandiaSPA.Service.UsuarioService;

import java.util.Optional;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Importar las librerias de Swagger para la documentacion de las API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios") //URL base para las definiciones HTTP
@CrossOrigin //Permitir ediciones desde cualquier origen
@Tag(name = "Usuario", description = "Operaciones sobre los usuarios del sistema")
public class UsuarioController {
    @Autowired
    private UsuarioService serv;

    @Operation(summary = "Registrar usuarios", description = "Registrar un nuevo usuario en el sistema")
    @PostMapping("/registrar") // Este metodo es el metodo para registrar un usuario en la tabla usuario
    public Usuario registrar(@RequestBody Usuario u) {    
        return serv.registrar(u);
    }

    // Metodo para autenticar usuario 
    @Operation(summary = "Iniciar Sesion", description = "Autentica la sesion de un usuario registrado con emal y password")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario u) {
        Optional<Usuario> user = serv.autenticar(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if(user.isPresent()) {
            response.put("result","OK");
            response.put("nombre",user.get().getNombre());
        } else {
            response.put("result","Error");
        }
        return response;
    }
}
