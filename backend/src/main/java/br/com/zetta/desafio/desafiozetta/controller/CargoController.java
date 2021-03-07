package br.com.zetta.desafio.desafiozetta.controller;

import br.com.zetta.desafio.desafiozetta.controller.dto.CargoDTO;
import br.com.zetta.desafio.desafiozetta.model.Cargo;
import br.com.zetta.desafio.desafiozetta.repository.CargoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    CargoRepository cargoRepository;

    public CargoController(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @GetMapping("")
    public List<CargoDTO> ListarCargos(@RequestParam(required = false) String ordem) {
        var cargo = cargoRepository.findAll();

        if (ordem.equals("asc")) {
            cargo = cargoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        } else if (ordem.equals("desc")) {
            cargo = cargoRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
        }

        return cargo
                .stream()
                .map(CargoDTO::Converter)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public void CadastrarCargo(@RequestBody CargoDTO dto) {

        var listaCargo = cargoRepository.findAll();

        final boolean[] existeNome = new boolean[1];

        listaCargo
                .stream()
                .forEach(c -> {
                    if (dto.getNome().equals(c.getNome())) {
                        existeNome[0] = true;
                    }
                });

        if(!existeNome[0]) {
            var cargo = new Cargo();
            cargo.setNome(dto.getNome());
            cargoRepository.save(cargo);
        }
    }

    @PutMapping("/{id}")
    public void AtualizarCargo(@PathVariable("id") long id, @RequestBody CargoDTO dto) throws Exception {
        var c = cargoRepository.findById(id);

        if (c.isPresent()) {
            var cargoSave = c.get();
            cargoSave.setNome(dto.getNome());
            cargoRepository.save(cargoSave);
        } else {
            throw new Exception("Cargo não encontrado!");
        }
    }
}
