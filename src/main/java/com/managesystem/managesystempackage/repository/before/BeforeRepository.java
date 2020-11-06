package com.managesystem.managesystempackage.repository.before;

import java.util.List;

import com.managesystem.managesystempackage.entity.BUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BeforeRepository {
    List<BUser> login(BUser bUser);
    void saveUser(BUser bUser);
}
