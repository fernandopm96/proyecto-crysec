package com.crysec.proyectocrysec.controllers;

import com.crysec.proyectocrysec.dto.AppointGroupAdmin;
import com.crysec.proyectocrysec.entities.CrysecUser;
import com.crysec.proyectocrysec.services.CrysecUserService;
import com.crysec.proyectocrysec.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT})
public class CrysecUserController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private CrysecUserService crysecUserService;

    @GetMapping("/companies/{companyId}/users")
    public List<CrysecUser> getCompanyUsers(@PathVariable String companyId) {
        return crysecUserService.getCompanyUsers(Long.parseLong(companyId));
    }
    @GetMapping("/groups/{groupId}/members")
    public List<CrysecUser> getGroupMembers(@PathVariable String groupId) {
        return groupService.membersGroup(Long.parseLong(groupId));
    }
    @GetMapping("/groups/{groupId}/admins")
    public List<CrysecUser> getGroupAdmins(@PathVariable String groupId) {
        return groupService.adminsGroup(Long.parseLong(groupId));
    }
    /*  @PutMapping("/groups/{groupId}/admins/appoint/{userId}")
    public void appointGroupAdmin(@PathVariable String groupId, @PathVariable String userId) {
        crysecUserService.appointGroupAdmin(groupId, userId);
    }*/

}
