package com.bolly.sservice.service.imp;

import com.bolly.sservice.entity.User;
import com.bolly.sservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(transactionManager = "transactionManager",rollbackFor = Exception.class)
    public void insert(User user) {
        String insertSql = "insert into user (name, age) value (:name,:age)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        int ret = jdbcTemplate.update(insertSql, sqlParameterSource);
        System.out.println(ret);
    }

    @Override
    public User get(long id) {
        String querySql = "SELECT * FROM USER where id=?";
        return jdbcTemplate.queryForObject(querySql, new BeanPropertyRowMapper<>(User.class),id);
    }

    @Override
    public List<User> getList(User cond) {
        StringBuilder querySql = new StringBuilder("SELECT * FROM USER where 1=1");
        if(Objects.nonNull(cond.getAge())) {
            querySql.append("and age="+cond.getAge());
        }

        if(Objects.nonNull(cond.getName())) {
            querySql.append("and name="+cond.getName());
        }
        return jdbcTemplate.queryForList(querySql.toString(),User.class);
    }

    @Override
    public int queryForInt() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER",Integer.class);
        return count;
    }

    public void preparedStatement() {
        jdbcTemplate.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return null;
            }
        }, new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                return null;
            }
        });
    }


}
