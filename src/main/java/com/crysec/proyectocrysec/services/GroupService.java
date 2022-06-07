package com.crysec.proyectocrysec.services;

import com.crysec.proyectocrysec.dto.GroupDto;
import com.crysec.proyectocrysec.entities.Company;
import com.crysec.proyectocrysec.entities.CrysecUser;
import com.crysec.proyectocrysec.entities.Group;
import com.crysec.proyectocrysec.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CrysecUserService crysecUserService;

    public List<Group> getCompanyGroups(Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return company.getGroups();
    }
    public Group getGroupById(Long id) {
        return groupRepository.getReferenceById(id);
    }
    public void createGroup(GroupDto groupDto) {
        Group group = new Group(groupDto.getName(), groupDto.getType());
        Company company = companyService.getCompanyById(groupDto.getCompanyId());
        var users = crysecUserService.getUsersById(groupDto.getMembers());
        group.setCompany(company);
        group.addMembersToGroup(users);
        groupRepository.save(group);
    }
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
    public List<CrysecUser> membersGroup(Long groupId) {
        return getGroupById(groupId).getMembers();
    }
    public List<CrysecUser> adminsGroup(Long groupId) {
        return getGroupById(groupId).getAdmins();
    }

    public void appointAdmin(Long groupId, Long userId) {
        Group group = groupRepository.getReferenceById(groupId);
        CrysecUser user = crysecUserService.getUserById(userId);
        boolean isAdmin = false;
        if(group.getAdmins().size() > 0) {
            for (CrysecUser admin: group.getAdmins()) {
                if(admin.getId() == user.getId()){
                    isAdmin = true;
                    System.out.println("Borrando usuario " + user.getId());
                    System.out.println(group.getAdmins().size());
                    group.removeAdmin(user);
                    System.out.println(group.getAdmins().size());
                    groupRepository.save(group);
                    break;
                }
            }

        }
        if(isAdmin){
            return;
        } else {
            group.addAdmin(user);
            groupRepository.save(group);
        }
    }

    public void deleteMember(Long groupId, Long userId) {
        Group group = groupRepository.getReferenceById(groupId);
        CrysecUser user = crysecUserService.getUserById(userId);
        group.removeMember(user);
        if(group.getAdmins().size() > 0) {
            group.getAdmins().forEach(admin -> {
                if(admin.getId() == userId){
                    group.removeAdmin(admin);
                }
            });
        }
        groupRepository.save(group);
    }



/*    public void updateName(GroupDto groupDto) {
        Group group = groupRepository.getReferenceById(groupDto.getId());
        group.setName(groupDto.getName());
    }
    public void updateType(GroupDto groupDto) {
        Group group = groupRepository.getReferenceById(groupDto.getId());
        group.setType(groupDto.getType());
    }
    public void appointAdmin(Long idGroup, Long idUser) {
        CrysecUser user = crysecUserService.getUserById(idUser);
        Group group = getGroupById(idGroup);
    }
    public void disappointAdmin(Long idGroup, Long idUser) {

    }
*/
}
