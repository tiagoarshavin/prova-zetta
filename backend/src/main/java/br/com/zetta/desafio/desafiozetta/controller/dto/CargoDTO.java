package br.com.zetta.desafio.desafiozetta.controller.dto;

import br.com.zetta.desafio.desafiozetta.model.Cargo;

public class CargoDTO {
    private Long id;
    private Long key;
    private String nome;

    public static CargoDTO Converter(Cargo c) {
        var dto = new CargoDTO();

        dto.setId(c.getId());
        dto.setKey(c.getId());

        dto.setNome(c.getNome());

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
}
