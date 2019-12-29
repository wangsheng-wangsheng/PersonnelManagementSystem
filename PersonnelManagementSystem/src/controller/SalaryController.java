package controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Salary;
import service.SalaryService;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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
            message.put("message", "员工工资修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
            message.put("message", "数据库操作异常");
        }catch(Exception e){
            e.printStackTrace();
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据request对象，获得代表参数的JSON字串
        String salary_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Staff对象
        Salary salaryToAdd = JSON.parseObject(salary_json, Salary.class);
        //响应
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            System.out.println(salaryToAdd.getBaseSalary());
            //增加Salary对象
            SalaryService.getInstance().add(salaryToAdd);
            //加入数据信息
            message.put("message", "员工工资添加成功");
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String staff_str = request.getParameter("staff_id");
        String no_str = request.getParameter("no");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            if(no_str==null){
                int staff_id = Integer.parseInt(staff_str);
                responseAttendance(staff_id, response);
            }else {
                responseSalaryNo(no_str,response);
            }

        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应message到前端
            response.getWriter().println(message);
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应message到前端
            response.getWriter().println(message);
        }
    }
    //响应一个考勤对象
    private void responseAttendance(int satff_id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String salary_json = null;
        Salary salary = SalaryService.getInstance().find(satff_id);
        if (salary == null){
            Salary salary1 = new Salary();
            salary1.setId(0);
            salary_json =JSON.toJSONString(salary1);
        }else {
            salary_json = JSON.toJSONString(salary);
        }
        //响应message到前端
        response.getWriter().println(salary_json);
    }
    private void responseSalaryNo(String no, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找考勤
        Collection salary = SalaryService.getInstance().findByStaffNo(no);
        String salary_json = JSON.toJSONString(salary, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(salary_json);
    }
}
