package controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import domain.Salary;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/salary.ctl")
public class SalaryController extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staff_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Attendance对象
        Salary salaryToAdd = JSON.parseObject(staff_json,Salary.class);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Salary对象
            SalaryService.getInstance().update(salaryToAdd);
            //加入数据信息
            message.put("message", "更新成功");
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String staff_str = request.getParameter("staffId");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            int staff_id = Integer.parseInt(staff_str);
            responseAttendance(staff_id, response);
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应message到前端
            response.getWriter().println(message);
        }catch(Exception e){
            message.put("message", "网络异常");
            //响应message到前端
            response.getWriter().println(message);
        }
    }
    //响应一个考勤对象
    private void responseAttendance(int satff_id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找考勤
        String salary = SalaryService.getInstance().find(satff_id);
        String attendance_json = JSON.toJSONString(salary);
        //响应message到前端
        response.getWriter().println(attendance_json);
    }
}
