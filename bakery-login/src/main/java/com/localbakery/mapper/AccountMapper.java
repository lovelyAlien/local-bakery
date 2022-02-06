package com.localbakery.mapper;

import com.localbakery.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO accounts(email , user_name) VALUES  (#{email}, #{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveAccount(Account account);

    @Select("SELECT * FROM accounts WHERE email = #{email}")
    Account findByEmail(@Param("email") String email);

    @Select("SELECT * FROM accounts WHERE id = #{id}")
    Account findById(@Param("id") Long id);
}
