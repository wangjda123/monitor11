package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
@Data
public class LoginDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用戶名
     */
    private String name;
    /**
     * 用户密码
     *
     */

    private String token;
    private List<PermissionDTO> permissionDTOList;

}
