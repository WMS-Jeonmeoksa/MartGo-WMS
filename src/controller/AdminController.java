package controller;

import model.dto.AdminDTO;
import model.dto.UserDTO;

public interface AdminController {
    //창고 관리자 메뉴
    void warehouseAdminMenu(AdminDTO admin);

    // 총 관리자 메뉴
    void superAdminMenu(AdminDTO admin);


}
