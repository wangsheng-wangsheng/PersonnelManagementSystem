package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login.ctl")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求中获取json数据
        String user_json = JSONUtil.getJSON(request);
        //将json数据转换成对象
        User userFromReq = JSON.parseObject(user_json, User.class);
        //获取用户名和密码
        String username = userFromReq.getUsername();
        String password = userFromReq.getPassword();
        JSONObject message = new JSONObject();
        User user = null;
        try {
            //使用登录方法
            user = UserService.getInstance().login(username,password);
            //如果user存在就表明登录成功
            if (user != null){
                //从请求中获取session如果没有则自动创建
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60 * 10);//set login time with 10 mins
                //向session中添加
                session.setAttribute("currentUser",user);
                //推送前台
                String teacher_json = JSON.toJSONString(user.getTeacher());
                response.getWriter().println(teacher_json);

            }else {
                //否则就是登录失败，用户名或密码错误
                message.put("message","用户名或密码错误");
                response.getWriter().println(message);
            }
        } catch (SQLException e) {
            //抓取错误
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }
    }
}
