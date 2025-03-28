package desafio.db.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

java
package com.exemplo.crud.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List enderecos;

    // Getters e Setters
}
java
package com.exemplo.crud.model;

import javax.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String cidade;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    // Getters e Setters
}
java
package com.exemplo.crud.repository;

import com.exemplo.crud.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository {
    boolean existsByCpf(String cpf);
}
java
package com.exemplo.crud.repository;

import com.exemplo.crud.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository {
}
`java
package com.exemplo.crud.controller;

import com.exemplo.crud.model.Pessoa;
import com.exemplo.crud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

@Autowired
private PessoaRepository pessoaRepository;

@GetMapping
public List listar() {
return pessoaRepository
json
     [
       {
         "id": 1,
         "nome": "João",
         "idade": 30,
         "enderecos": [
           {
             "rua": "Rua A",
             "numero": "123",
             "cidade": "São Paulo"
           }
         ]
       }
     ]
	 json
     {
       "nome": "Maria",
       "idade": 25,
       "enderecos": [
         {
           "rua": "Rua B",
           "numero": "456",
           "cidade": "Rio de Janeiro"
         }
       ]
     }
	 json
     {
       "id": 2,
       "nome": "Maria",
       "idade": 25,
       "enderecos": [...]
     }
	 json
     {
       "id": 2,
       "nome": "Maria Silva",
       "idade": 26,
       "enderecos": [...]
     }
	 json
     {
       "mensagem": "Pessoa excluída com sucesso."
     }
	 json
     {
       "idade": 30
     }





