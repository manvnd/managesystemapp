package com.managesystem.managesystempackage.repository.admin;

import java.util.List;

import com.managesystem.managesystempackage.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminRepository {
    List<Admin> adminLogin(Admin admin);
}
