package controller;
import service.AttendanceService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
@WebServlet("/attendance.ctl")
public class AttendanceController extends HttpServlet {
    //    GET, http://localhost:8080/attendance.ctl?id=1, 查询id=1的考勤
    //    GET, http://localhost:8080/attendance.ctl, 查询所有的考勤
    //    POST, http://localhost:8080/attendance.ctl, 增加考勤
    //    PUT, http://localhost:8080/attendance.ctl, 修改考勤
    //    DELETE, http://localhost:8080/attendance.ctl?id=1, 删除id=1的考勤
    /**
     * 方法-功能
     * put 修改
     * post 添加
     * delete 删除
     * get 查找
     */
    //请使用以下JSON测试增加功能
    //{"name":"人事部","remarks":"","staff_id":"1"}

    //请使用以下JSON测试修改功能
    //{"id":1,"name":"人事部","remarks":"","staff_id":"1"}
    /**
     * POST,http://localhost:8080/attendance.ctl
     * 增加一个考勤对象：将来自前端请求的JSON对象，增加到数据库表中
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据request对象，获得代表参数的JSON字串
        String attendance_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Attendance对象
        Attendance attendanceToAdd = JSON.parseObject(attendance_json, Attendance.class);
        //前台没有为id赋值，此处模拟自动生成id。Dao能实现数据库操作时，应删除此语句。
        attendanceToAdd.setId(4 + (int)(Math.random()*100));
        //响应
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Attendance对象
            AttendanceService.getInstance().add(attendanceToAdd);
            //加入数据信息
            message.put("message", "增加成功");
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }

    /**
     * DELETE, http://localhost:8080/attendance.ctl?id=1
     * 删除一个考勤对象：根据来自前端请求的id，删除数据库表中id的对应记录
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
            //到数据库表中删除对应的考勤
            AttendanceService.getInstance().delete(id);
            //加入数据信息
            message.put("message", "删除成功");
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }


    /**
     * PUT, http://localhost:8080/attendance.ctl
     * 修改一个考勤对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String attendance_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Attendance对象
        Attendance attendanceToAdd = JSON.parseObject(attendance_json, Attendance.class);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Attendance对象
            AttendanceService.getInstance().update(attendanceToAdd);
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
    /**
     * GET, http://localhost:8080/attendance.ctl?id=1, 查询id=1的考勤
     * GET, http://localhost:8080/attendance.ctl, 查询所有的考勤
     * 响应一个或所有考勤对象
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
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //如果id = null, 表示响应所有考勤对象，否则响应id指定的考勤对象
            if (id_str == null&&staff_str==null) {
                responseAttendances(response);
            }else {
                int id = Integer.parseInt(id_str);
                if(staff_str==null) {
                    responseAttendance(id, response);
                }else if(staff_str.equals("staffId")){
                    responseDepSch(id, response);
                }
            }
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
    private void responseAttendance(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找考勤
        Attendance attendance = AttendanceService.getInstance().find(id);
        String attendance_json = JSON.toJSONString(attendance);
        //响应message到前端
        response.getWriter().println(attendance_json);
    }
    //响应所有考勤对象
    private void responseAttendances(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有考勤
        Collection<Attendance> attendances = AttendanceService.getInstance().findAll();
        String attendances_json = JSON.toJSONString(attendances, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(attendances_json);
    }
    //响应school_id的所有考勤对象
    private void responseDepSch(int staff_id,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有考勤
        Collection<Attendance> attendances = AttendanceService.getInstance().findAllByStaff(staff_id);
        String attendances_json = JSON.toJSONString(attendances, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(attendances_json);
    }
}
