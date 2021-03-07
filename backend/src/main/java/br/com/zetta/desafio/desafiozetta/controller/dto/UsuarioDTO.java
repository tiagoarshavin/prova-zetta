package br.com.zetta.desafio.desafiozetta.controller.dto;

import br.com.zetta.desafio.desafiozetta.model.Usuario;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioDTO {
    private Long id;
    private Long key;
    private String nome;
    private String cpf;
    private LocalDateTime data_nascimento;
    private char sexo;
    private LocalDateTime data_cadastro;
    private CargoDTO cargo;
    private List<PerfilDTO> perfis;

    public static UsuarioDTO Converter(Usuario u){
        var dto = new UsuarioDTO();

        dto.setId(u.getId());
        dto.setKey(u.getId());

        dto.setNome(u.getNome());
        dto.setCpf(u.getCpf());
        dto.setData_nascimento(u.getData_nascimento());
        dto.setSexo(u.getSexo());
        dto.setData_cadastro(u.getData_cadastro());

        try{
            //Adicionando o cargo
            var cargo = u.getCargo();
            dto.setCargo(CargoDTO.Converter(cargo));

        }catch(Exception e){

        }


        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDateTime data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

    public List<PerfilDTO> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<PerfilDTO> perfis) {
        this.perfis = perfis;
    }
}
