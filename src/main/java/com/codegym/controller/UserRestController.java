package com.codegym.controller;


import com.codegym.model.Role;
import com.codegym.service.JwtService;
import com.codegym.model.Account;
import com.codegym.service.accountService.AccountService;
import com.codegym.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/rest")
public class UserRestController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    /* ---------------- GET ALL USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllUser() {
        System.out.println("sad");
        return new ResponseEntity<List<Account>>(accountService.findAll(), HttpStatus.OK);
    }
    /* ---------------- GET USER BY ID ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            return new ResponseEntity<Object>(account, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
    }
    /* ---------------- CREATE NEW USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody Account account) {
        Set<Role> setRole = new HashSet<>();
        setRole.add(roleService.findById(2L));
        account.setRoles(setRole);
        if (accountService.save(account)) {
            return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
        }
    }
    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Account account) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (accountService.checkLogin(account)) {
                result = jwtService.generateTokenLogin(account.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }
}