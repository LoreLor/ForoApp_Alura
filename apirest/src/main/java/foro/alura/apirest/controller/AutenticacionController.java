package foro.alura.apirest.controller;

import foro.alura.apirest.domain.usuario.DTOAutenticacionUsuario;
import foro.alura.apirest.domain.usuario.Usuario;
import foro.alura.apirest.security.DatosJWToken;
import foro.alura.apirest.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWToken> realizarLogin(@RequestBody @Valid DTOAutenticacionUsuario datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DatosJWToken(tokenJWT));
    }
}
