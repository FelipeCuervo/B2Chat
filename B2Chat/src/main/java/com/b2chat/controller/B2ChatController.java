package com.b2chat.controller;

import com.b2chat.entity.User;
import com.b2chat.entity.UserRepository;
import com.b2chat.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
//@Api(tags = "UsersB2Chat")
public class B2ChatController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    @PostMapping("/createUser")
//    @ApiOperation(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        try{
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario Creado Correctamente");
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/retrieveById")
//    @ApiOperation(value = "retrieveById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrieveById(@RequestParam(name = "id") Long id){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.retrieveById(id));
        }catch (Exception e) {
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @DeleteMapping("/deleteById")
//    @ApiOperation(value = "deleteById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@RequestParam(name = "id") Long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(" Usuario eliminado correctamente");
        } catch (ResponseStatusException e) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("updateUser")
//    @ApiOperation(value = "updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody User user){
       try{
           userService.update(user);
           return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario con Id: "+user.getId()+" no fue encontrado");
        }
    }

}
