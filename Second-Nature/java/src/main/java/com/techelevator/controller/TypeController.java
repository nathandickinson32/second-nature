package com.techelevator.controller;

import com.techelevator.dao.TypeDao;
import com.techelevator.model.Type;
import com.techelevator.model.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/type")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class TypeController {
    @Autowired
    private TypeDao typeDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(path = "/create")
    public Type createType(@RequestBody TypeDto typeDto, Principal principal){
        System.out.println(LocalDateTime.now() + " [Type Controller] User: " + principal.getName() + " is creating a new type.");
        return typeDao.createType(typeDto);
    }

    @GetMapping(path = "/all")
    public List<Type> getAllTypes(Principal principal){
        System.out.println(LocalDateTime.now() + "[Type Controller] User " + principal.getName() + " is getting a list of all types.");
        return typeDao.getAllTypes();
    }

    @GetMapping(path = "/{typeId}")
    public Type getTypeByTypeId(@PathVariable int typeId, Principal principal){
        System.out.println(LocalDateTime.now() + "[Type Controller] User " + principal.getName() + " is getting type by ID: " + typeId);
        return typeDao.getTypeByTypeId(typeId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update")
    public Type updateType(@RequestBody Type type, Principal principal){
        System.out.println(LocalDateTime.now() + "[Type Controller] User " + principal.getName() + " is updating type by ID: " + type.getTypeId());
        return typeDao.updateType(type);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/delete/{typeId}")
    public void deleteType(@PathVariable int typeId, Principal principal){
        System.out.println(LocalDateTime.now() + "[Type Controller] User " + principal.getName() + " is deleting type by ID: " + typeId);
        typeDao.deleteType(typeId);
    }
}
