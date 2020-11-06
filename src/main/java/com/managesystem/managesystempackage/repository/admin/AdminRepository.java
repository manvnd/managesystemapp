package com.managesystem.managesystempackage.repository.admin;

import java.util.List;

import com.managesystem.managesystempackage.entity.AUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminRepository {
    List<AUser> login(AUser aUser);
}
