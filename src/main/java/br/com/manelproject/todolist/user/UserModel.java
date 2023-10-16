package br.com.manelproject.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data //Importar os métodos getters & setters para a aplicação de forma mais rápida
@Entity(name = "tb_users") //Gerando tabela de nome "tb_users"
public class UserModel {
    
    @Id //Indicar que será a primary key
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    //@Column(name = "usuarios") -> Mudar o nome da coluna abaixo
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp //Registrar o momento em que um determinado registro ou objeto foi criado.
    private LocalDateTime createdAt;


    
}
