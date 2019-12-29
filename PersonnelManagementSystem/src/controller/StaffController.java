package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Staff;
import service.StaffService;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 将所有方法组织在一个Controller(Servlet)中
 */
@WebServlet("/staff.ctl")
public class StaffController extends HttpServlet {
    //    GET, http://localhost:8080/staff.ctl?id=1, 查询id=1的员工
    //    GET, http://localhost:8080/staff.ctl, 查询所有的员工
    //    POST, http://localhost:8080/staff.ctl, 增加员工
    //    PUT, http://localhost:8080/staff.ctl, 修改员工
    //    DELETE, http://localhost:8080/staff.ctl?id=1, 删除id=1的员工
    /**
     * 方法-功能
     * put 修改
     * post 添加
     * delete 删除
     * get 查找
     */
    //请使用以下JSON测试增加功能

    //请使用以下JSON测试修改功能
    /**
     * POST,http://localhost:8080/staff.ctl
     * 增加一个对象：将来自前端请求的JSON对象，增加到数据库表中
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据request对象，获得代表参数的JSON字串
        String staff_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Staff对象
        Staff staffToAdd = JSON.parseObject(staff_json, Staff.class);
        //响应
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Staff对象
            StaffService.getInstance().add(staffToAdd);
            //加入数据信息
            message.put("message", "增加成功");
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
        }
        //响应message到前端
        response.getWriter().println(message);
    }

    /**
     * DELETE, http://localhost:8080/staff.ctl?id=1
     * 删除一个对象：根据来自前端请求的id，删除数据库表中id的对应记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //到数据库表中删除对应的员工
            boolean deleted = StaffService.getInstance().delete(id);
            if (deleted) {
                //加入数据信息
                message.put("message", "删除成功");
            }else {
                message.put("message", "删除失败");
            }
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    /**
     * PUT, http://localhost:8080/staff.ctl
     * 修改一个对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staff_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Staff对象
        Staff staffToAdd = JSON.parseObject(staff_json, Staff.class);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Staff对象
            boolean updated = StaffService.getInstance().update(staffToAdd);
            if (updated) {
                //加入数据信息
                message.put("message", "更新成功");
            }else {
                message.put("message", "更新失败");
            }
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    /**
     * GET, http://localhost:8080/staff.ctl?id=1, 查询id=1的员工
     * GET, http://localhost:8080/staff.ctl, 查询所有的员工
     * 响应一个或所有员工对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        String staff_str = request.getParameter("staffId");
        String no_str = request.getParameter("no");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //如果id = null, 表示响应所有部门对象，否则响应id指定的部门对象
            if (id_str == null && staff_str==null && no_str==null) {
                responseStaffs(response);
            }else {
                if (staff_str == null && no_str==null) {
                    int id = Integer.parseInt(id_str);
                    responseStaff(id, response);
                }else if(id_str == null && no_str==null){
                    int staffId = Integer.parseInt(staff_str);
                    responseByStaffId(staffId,response);
                }else {
                    responseByStaffNo(no_str,response);
                }
            }
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应message到前端
            response.getWriter().println(message);
        }catch(Exception e){
            e.printStackTrace();
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应message到前端
            response.getWriter().println(message);
        }
    }
    //响应一个员工对象
    private void responseStaff(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找员工
        Staff staff = StaffService.getInstance().find(id);
        String staff_json = JSON.toJSONString(staff);
        //响应message到前端
        response.getWriter().println(staff_json);
    }

    private void responseByStaffId(int staffId, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找员工
        Staff staff = StaffService.getInstance().findByStaffId(staffId);
        String staff_json = JSON.toJSONString(staff);
        //响应message到前端
        response.getWriter().println(staff_json);
    }
    //响应所有员工对象
    private void responseStaffs(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有员工
        Collection<Staff> staff = StaffService.getInstance().findAll();
        String staff_json = JSON.toJSONString(staff, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(staff_json);
    }
    private void responseByStaffNo(String no, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找员工
        Staff staff = StaffService.getInstance().findByStaffNo(no);
        String staff_json = JSON.toJSONString(staff);
        //响应message到前端
        response.getWriter().println(staff_json);
    }
}
