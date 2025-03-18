package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDto {
    private String adminId;
    private String adminName;
    private String adminPassword;
    private String Email;
    private String phone;
    private String address;
    private String role; //창고관리자 또는 총관리자
    private Integer warehouseId;

    public AdminDto() {
    }
}
