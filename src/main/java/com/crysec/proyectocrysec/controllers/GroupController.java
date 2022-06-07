package com.crysec.proyectocrysec.controllers;

import com.crysec.proyectocrysec.dto.GroupDto;
import com.crysec.proyectocrysec.entities.Group;
import com.crysec.proyectocrysec.services.CrysecUserService;
import com.crysec.proyectocrysec.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private CrysecUserService crysecUserService;

    @GetMapping("/companies/{companyId}/groups")
    public List<Group> getCompanyGroups(@PathVariable String companyId) {
        return groupService.getCompanyGroups(Long.parseLong(companyId));
    }

    @PostMapping("/groups")
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupService.createGroup(groupDto);
    }

    @DeleteMapping("/groups/{groupId}")
    public void deleteGroup(@PathVariable(value = "groupId") String groupId) {
        groupService.deleteGroup(Long.parseLong(groupId));
    }

    @PutMapping("/groups/{groupId}/admins/{userId}")
    public void appointAdmin(@PathVariable String groupId, @PathVariable String userId) {
        groupService.appointAdmin(Long.parseLong(groupId), Long.parseLong(userId));
    }
    @DeleteMapping("/groups/{groupId}/members/{userId}")
    public void deleteMember(@PathVariable String groupId, @PathVariable String userId) {
        groupService.deleteMember(Long.parseLong(groupId), Long.parseLong(userId));

    }

}
