package com.mutts_app.mapper;

import com.mutts_app.repositories.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {


    String SELECT_ALL_USERS = "select * from `test-db`.users";

    String SELECT_BY_FIRST_LAST_NAME = "select * from `test-db`.users " +
            "where first_name = #{param1} and last_name = #{param2}";

    String INSERT_USER = "INSERT INTO `test-db`.users (first_name, last_name) " +
            "VALUES (#{first_name}, #{last_name})";

    String DELETE_USER = "UPDATE `test-db`.users set isActive = false where id = #{id} ";

    String UPDATE_USER = "UPDATE `test-db`.`users` SET `first_name` = #{first_name}, " +
            "`last_name` = #{last_name}, `isActive` = #{isActive} WHERE (`id` = #{id})";

    String SELECT_BY_ID = "SELECT * from `test-db`.users where id =#{id}";

    // bring queries to annotations and mybatis does the rest of the work
    // will also put them into array list as well
    @Select(SELECT_ALL_USERS)
    public ArrayList<User> getAllUsers();

    @Insert(INSERT_USER)
    public int insertUser(User user);


    @Select(SELECT_BY_FIRST_LAST_NAME)
    User findUserByFirstNameLastName(String first_name, String last_name);

    @Update(DELETE_USER)
    int makeUserInactive(int id);
    // have to be int because they return the number of rows affected

    @Update(UPDATE_USER)
    int patchUser(User user);
    // have to be int because they return the number of rows affected

    @Select(SELECT_BY_ID)
    User findById(int id);

}
