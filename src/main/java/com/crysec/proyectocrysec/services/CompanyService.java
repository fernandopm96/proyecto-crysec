package com.crysec.proyectocrysec.services;

import com.crysec.proyectocrysec.entities.Company;
import com.crysec.proyectocrysec.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }
}
