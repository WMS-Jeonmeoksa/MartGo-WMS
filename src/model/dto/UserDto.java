package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String phone;
    private String email;
    private String address;
    private String role; // 회원 또는 거래처
    private int admin_id;

    public UserDto() {
    }
}
