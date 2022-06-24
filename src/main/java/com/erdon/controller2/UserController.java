package com.erdon.controller2;

import com.erdon.entity.User;
import com.erdon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    protected ModelAndView login(HttpServletRequest req, HttpServletResponse resp, User user) {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");

//        user.setUsername(username);
//        user.setPassword(password);
//        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        ModelAndView mv = new ModelAndView();

        User login = userService.login(user);
        if (login != null) {
            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            mv.addObject("msg", "");
            mv.setViewName("pages/user/login_success.jsp");
//        }else if (login==2){
//            req.setAttribute("msg","密码错误，请重新输入！");
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            mv.addObject("msg", "用户名或密码错误,请重新输入！");
            mv.addObject("username", user.getUsername());
            mv.setViewName("/pages/user/login.jsp");
        }
        return mv;
    }

    @RequestMapping("/register")
    protected ModelAndView register(HttpServletRequest req, User user, String code) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        ModelAndView mv = new ModelAndView();
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
//        String code = req.getParameter("code");

//        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existsUser(user.getUsername())) {
                mv.addObject("msg", "用户名已存在");
                mv.addObject("username", user.getUsername());
                mv.addObject("email", user.getEmail());
                mv.setViewName("/pages/user/regist.jsp");

            } else {
                int i = userService.addUser(user);
                if (i != 0) {
                    mv.setViewName("/pages/user/regist_success.jsp");
                } else {
                    mv.addObject("msg", "注册失败!");
                    mv.addObject("username", user.getUsername());
                    mv.addObject("email", user.getEmail());
                    mv.setViewName("/pages/user/regist.jsp");
                }
            }
        } else {
            mv.addObject("username", user.getUsername());
            mv.addObject("email", user.getEmail());
            mv.addObject("msg", "验证码错误！");
            mv.setViewName("/pages/user/regist.jsp");
        }
        return mv;
    }

    @RequestMapping("/logoutUser")
    public String logoutUser(HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return "redirect:/index.jsp";
    }

    @ResponseBody
    @RequestMapping("/ajaxExistsUsername")
    public Map ajaxExistsUsername(String username) throws IOException {
//        String username = req.getParameter("username");
        Boolean aBoolean = userService.existsUser(username);
        Map<String, Object> map = new HashMap<>();
        map.put("aBoolean", aBoolean);
//        Gson gson = new Gson();
//        String s = gson.toJson(map);
//        resp.getWriter().write(s);
        return map;
    }
}
