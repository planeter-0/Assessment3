package ServiceTest;

import data_access.UserDAO;
import entity.User;
import service.UserService;

public class UserServiceTest {
    /**
     * 分别取消注释进行测试
     */
    public static void main(String[] args) throws Exception {
        UserService us = new UserService();
        //注册
//        us.register();

        //登录
//        User user = new User("user1","1"); // 有效用户
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login();
//
//        User user_ = new User("u3423","1"); // 无效用户
//        UserDAO userDAO_ = new UserDAO(user_);
//        UserService userService_ = new UserService(userDAO_);
//        userService_.login();

        //查询自己的借阅记录 (登陆后)
//        User user = new User("user1","1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login(); //登录
//        userService.queryBorrowRecord(); //查询

        //根据部分图书名进行模糊查询
//        User user = new User("user1","1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.queryBookByName("入门");

        //借书(登陆后)
//        User user = new User("user1","1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login(); //登录
//        userService.borrowBook(1); //已被借走

//        User user = new User("user1","1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login(); //登录
//        userService.borrowBook(3); //未被借走

//        User user = new User("user1","1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login(); //登录
//        userService.borrowBook(1234); //无效的id

        //还书
//        User user = new User("user1", "1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login(); //登录
//        userService.returnBook(3); //有效的id

//        User user = new User("user1", "1");
//        UserDAO userDAO = new UserDAO(user);
//        UserService userService = new UserService(userDAO);
//        userService.login(); //登录
//        userService.returnBook(524); //无效的id
    }
}
