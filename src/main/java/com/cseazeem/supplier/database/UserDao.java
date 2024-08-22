package com.cseazeem.supplier.database;

import com.cseazeem.supplier.entity.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    @SqlUpdate("INSERT INTO users (full_name, email, password, created_at, updated_at) VALUES (:fullName, :email, :password, :createdAt, :updatedAt)")
    void insert(@BindBean User user);

    @SqlQuery("SELECT * FROM users WHERE email = :email")
    @RegisterBeanMapper(User.class)
    Optional<User> findByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM users")
    List<User> findAll();

    @SqlUpdate("INSERT INTO users (full_name, email, password, created_at, updated_at) VALUES (:fullName, :email, :password, NOW(), NOW())")
    void save(@Bind("fullName") String fullName, @Bind("email") String email, @Bind("password") String password);

}
