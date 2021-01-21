import data_access.*;
import entity.*;
import service.AdminService;
import service.UserService;
import utils.JDBCUtils;

public class Test {
    public static void main(String[] args) throws Exception {
//        //用户服务测试
//        UserService us = new UserService();
//        // 1.注册
//        us.register();
//
//
//        //管理员服务测试
//        AdminService as = new AdminService();
        System.out.println(JDBCUtils.isTableExist("ds"));
    }
}
