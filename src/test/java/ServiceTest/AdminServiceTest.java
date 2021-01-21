package ServiceTest;

import service.AdminService;

public class AdminServiceTest {
    public static void main(String[] args) throws Exception {
        //登陆测试
        System.out.println(AdminService.login());

        //展示表测试
        AdminService.viewTable("users"); //存在的表
        AdminService.viewTable("sdf");  //不存在的表

        //根据user_id查询某用户的所有借阅记录测试
        AdminService.queryRecordOfUser();

    }
}
