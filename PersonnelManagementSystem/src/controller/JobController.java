package controller;
import domain.Job;
import service.JobService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import util.JSONUtil;
import javax.servlet.*;
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
@WebServlet("/job.ctl")
public class JobController extends HttpServlet {
    //    GET, http://localhost:8080/job.ctl?id=1, 查询id=1的考勤
    //    GET, http://localhost:8080/job.ctl, 查询所有的考勤
    //    POST, http://localhost:8080/job.ctl, 增加考勤
    //    PUT, http://localhost:8080/job.ctl, 修改考勤
    //    DELETE, http://localhost:8080/job.ctl?id=1, 删除id=1的考勤
    /**
     * 方法-功能
     * put 修改
     * job 添加
     * delete 删除
     * get 查找
     */
    //请使用以下JSON测试增加功能
    //{"name":"经理","remarks":"","department_id":1}

    //请使用以下JSON测试修改功能
    //{"name":"副经理","id":1,"remarks":"","department_id":1}
    /**
     * POST,http://localhost:8080/job.ctl
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
        String job_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Job对象
        Job jobToAdd = JSON.parseObject(job_json, Job.class);
        //前台没有为id赋值，此处模拟自动生成id。Dao能实现数据库操作时，应删除此语句。
        jobToAdd.setId(4 + (int)(Math.random()*100));
        //响应
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Job对象
            JobService.getInstance().add(jobToAdd);
            //加入数据信息
            message.put("message", "增加成功");
        }catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    /**
     * DELETE, http://localhost:8080/job.ctl?id=1
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
            JobService.getInstance().delete(id);
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
     * PUT, http://localhost:8080/job.ctl
     * 修改一个考勤对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String department_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Job对象
        Job jobToAdd = JSON.parseObject(department_json, Job.class);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Job对象
            JobService.getInstance().update(jobToAdd);
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
     * GET, http://localhost:8080/job.ctl?id=1, 查询id=1的考勤
     * GET, http://localhost:8080/job.ctl, 查询所有的考勤
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
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //如果id = null, 表示响应所有学院对象，否则响应id指定的学院对象
            if (id_str == null) {
                responseJobs(response);
            } else {
                int id = Integer.parseInt(id_str);
                responseJob(id, response);
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
    private void responseJob(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找考勤
        Job job = JobService.getInstance().find(id);
        String job_json = JSON.toJSONString(job);
        //响应message到前端
        response.getWriter().println(job_json);
    }
    //响应所有考勤对象
    private void responseJobs(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有考勤
        Collection<Job> jobs = JobService.getInstance().findAll();
        String jobs_json = JSON.toJSONString(jobs, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(jobs_json);
    }

}
