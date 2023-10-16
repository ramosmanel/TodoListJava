package br.com.manelproject.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/*
 * Modificador
 * public
 * private
 * protected
 */

 @RestController
 @RequestMapping("/users")
public class UserController {
    
    @Autowired //Permite que o Spring gerenciar o ciclo
    private IUserRepository userRepository;

     //Indicar que o corpo da solicitação HTTP deve ser convertido em um objeto Java do tipo "UserModel"
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) { //Permite o retorno personalizando
        var user = this.userRepository.findByUsername(userModel.getUsername());

        //Mensagem de Erro
        //Status Code

        if(user != null) {
            System.out.println("Usuário já existente!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente!");
        }

        var passwordHashred= BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
