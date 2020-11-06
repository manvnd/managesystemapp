package com.managesystem.managesystempackage.service.before;

import com.managesystem.managesystempackage.entity.BUser;
import com.managesystem.managesystempackage.repository.before.BeforeRepository;
import com.managesystem.managesystempackage.service.before.BeforeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BeforeServiceImpl implements BeforeService {
    @Autowired
    private BeforeRepository beforeRepository;
    @Override
    public String login(BUser bUser, HttpSession session, Model model) {
        List<BUser> list = beforeRepository.login(bUser);
        if(list.size() > 0) {//登录成功
//            session.setAttribute("buser", bUser);
//            if (bUser.getIsTeacher())
            return "before/home";
//            else
//                return "forward:/";
        }else {//登录失败
            model.addAttribute("errorMessage", "用户名或密码错误！");
            return "before/login";
        }
    }
    @Override
    public void saveUser(BUser bUser, Model model) {
        beforeRepository.saveUser(bUser);
    }
}
