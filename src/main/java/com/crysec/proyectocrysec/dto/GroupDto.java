package com.crysec.proyectocrysec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupDto {
    private String name;
    private String type;
    private List<Long> members;
    private Long companyId;
}
