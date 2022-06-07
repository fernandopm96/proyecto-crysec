package com.crysec.proyectocrysec.services;

import com.crysec.proyectocrysec.entities.CrysecUser;
import com.crysec.proyectocrysec.entities.Group;
import com.crysec.proyectocrysec.repositories.CrysecUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrysecUserService {

    @Autowired
    private CrysecUserRepository crysecUserRepository;
    @Autowired
    private CompanyService companyService;

    public List<CrysecUser> getCompanyUsers(Long companyId) {
        return companyService.getCompanyById(companyId).getUsers();
    }

    public CrysecUser getUserById(Long id) {
        return crysecUserRepository.getReferenceById(id);
    }

    public List<CrysecUser> getUsersById(List<Long> ids) {
        List<CrysecUser> users = new ArrayList<CrysecUser>();
        ids.forEach((x) -> {
            users.add(crysecUserRepository.getReferenceById(x));
        });
        return users;
    }

}
