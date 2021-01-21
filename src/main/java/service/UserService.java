package service;

import data_access.*;
import entity.*;

import java.util.Scanner;

public class UserService {
    //    public BookDAO bookDAO;
//    public AuthorDAO authorDAO;
    public UserDAO userDAO;
//    public BorrowRecord recordDAO;
//
//    public UserService(BookDAO bookDAO, AuthorDAO authorDAO, UserDAO userDAO, BorrowRecord recordDAO) {
//        this.bookDAO = bookDAO;
//        this.authorDAO = authorDAO;
//        this.userDAO = userDAO;
//        this.recordDAO = recordDAO;
//    }


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService() {

    }

    //注册服务
    public void register() throws Exception {
        Scanner in = new Scanner(System.in);
        //实例化一个实体层类对象
        User user = new User();
        //表现
        System.out.println("请输入用户名:");
        user.setUsername(in.nextLine());
        System.out.println("请输入密码:");
        user.setPassword(in.nextLine());
        System.out.println("请输入手机号:");
        user.setPhone_num(in.nextLine());
        System.out.println("请输入邮箱:");
        user.setEmail(in.nextLine());

        //实例化一个数据访问层对象，与UserService关联,参数为实体层类对象。
        this.userDAO = new UserDAO(user);
        //userDAO访问数据库
        try {
            userDAO.create();
        } catch (Exception e) {
            System.out.println("DAO层错误");
        }
    }
//    //登录
//    public void login(){
//
//    }
//    //查询自己的借阅记录
//    public void queryBorrowRecord(){
//
//    }
//    //根据部分图书名进行模糊查询
//    public Book queryBookByName(String partialName){
//
//    }
//    //借书
//    public void borrowBook(String id){
//
//    }
//    //还书
//    public void returnBook(String id){
//
}

