package br.com.zetta.desafio.desafiozetta.controller;

import br.com.zetta.desafio.desafiozetta.controller.dto.UsuarioDTO;
import br.com.zetta.desafio.desafiozetta.model.Cargo;
import br.com.zetta.desafio.desafiozetta.model.Usuario;
import br.com.zetta.desafio.desafiozetta.repository.PerfilRepository;
import br.com.zetta.desafio.desafiozetta.repository.UsuarioRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioRepository usuarioRepo;
    PerfilRepository perfilRepo;

    public UsuarioController(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        this.usuarioRepo = usuarioRepository;
        this.perfilRepo = perfilRepository;
    }

    @GetMapping("")
    public List<UsuarioDTO> ListarUsuarios() {

        var usuarios = usuarioRepo.findAll();

        return usuarios
                .stream()
                .map(UsuarioDTO::Converter)
                .collect(Collectors.toList());

    }

    @PostMapping("")
    public UsuarioDTO CadastrarUsuario(@RequestBody UsuarioDTO dto) throws ResponseStatusException {

        // validações
        if (dto.getNome().equals("") || dto.getCpf().equals("")) {
            throw new ResponseStatusException(
                    HttpStatus.ACCEPTED, "O nome ou o cpf está vazio!");
        }

        //declara uma variavel do tipo perfil que o dto aceita
        Usuario usuario = new Usuario();

        usuario.setCpf(dto.getCpf());

        //verificando se cpf existe na base
        Example<Usuario> exampleUsuario = Example.of(usuario);
        var existeCpf = usuarioRepo.findOne(exampleUsuario);

        if (existeCpf.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.ACCEPTED, "O cpf já está cadastrado!");
        }

        //seta as propriedades vindo do body do objeto usuario
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());

        var loc = new Locale("por");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy",loc);
        LocalDate ld = LocalDate.parse(dto.getData_nascimento(), formatter);
        LocalDateTime dateTime = LocalDateTime.of(ld, LocalTime.of(0,0));
        usuario.setData_nascimento(dateTime);

        usuario.setSexo(dto.getSexo());
        usuario.setData_cadastro(LocalDateTime.now());

        // verificar se cargo e vazio
        if (dto.getCargo() == null) {
            throw new ResponseStatusException(
                    HttpStatus.ACCEPTED, "Voce deve informar um cargo!");
        }

        //adicionar o cargo
        var cargo = new Cargo();
        cargo.setId(dto.getCargo().getId());
        usuario.setCargo(cargo);

        // salva o usuario no banco atraves do repository
        usuarioRepo.save(usuario);

        return UsuarioDTO.Converter(usuario);
    }

}
