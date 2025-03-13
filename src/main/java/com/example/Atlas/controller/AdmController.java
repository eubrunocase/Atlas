package com.example.Atlas.controller;

import com.example.Atlas.model.Administrador;
import com.example.Atlas.service.AdmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atlas/administrador")
public class AdmController {

    private final AdmService admService;

    public AdmController(AdmService admService) {
        this.admService = admService;
    }

    @PostMapping
    public ResponseEntity<Administrador> create (@RequestBody Administrador administrador) {
        System.out.println("recebendo ADM no controller " + administrador);
        Administrador savedADM = admService.save(administrador);
        return ResponseEntity.status((HttpStatus.CREATED)).body(savedADM);
    }

    @GetMapping
    public List<Administrador> GetAll () {
        return admService.findAll();
    }

    @DeleteMapping
    public void delete (Long id) {
        admService.delete(id);
    }

    @PutMapping
    public Administrador update (@PathVariable Long id,@RequestBody Administrador administrador) {
        administrador.setId(id);
        return admService.save(administrador);
    }

}
