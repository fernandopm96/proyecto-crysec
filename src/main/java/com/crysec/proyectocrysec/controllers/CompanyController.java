package com.crysec.proyectocrysec.controllers;

import com.crysec.proyectocrysec.entities.Company;
import com.crysec.proyectocrysec.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/holamundo")
    public String holaMundo() {
        return "HOLA";
    }
    @GetMapping("/companies/{companyId}")
    public Company getCompany(@PathVariable String companyId) {
        return companyService.getCompanyById(Long.parseLong(companyId));
    }
}
