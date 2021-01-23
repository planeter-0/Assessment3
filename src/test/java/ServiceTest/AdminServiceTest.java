package ServiceTest;

import service.AdminService;

public class AdminServiceTest {
    /**
     * 分别取消注释进行测试
     */
    public static void main(String[] args) throws Exception {
        //登陆测试
//        System.out.println(AdminService.login());

        //展示表测试
//        AdminService.viewTable("users"); //存在的表
//        AdminService.viewTable("sdf");  //不存在的表

        //根据user_id查询某用户的所有借阅记录测试
//        AdminService.queryRecordOfUser(3); //有效id
//        AdminService.queryRecordOfUser(6); //无效id

        //根据book_id查询某图书的所有借阅历史
//        AdminService.queryRecordOfBook(5);   //有效id
//        AdminService.queryRecordOfBook(122); //无效id
    }
}
