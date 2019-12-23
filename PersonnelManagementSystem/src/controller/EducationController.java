package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Contract;
import domain.Education;
import service.ContractService;
import service.EducationService;
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
@WebServlet("/education.ctl")
public class EducationController extends HttpServlet {
    //    GET, http://localhost:8080/education.ctl?id=1, 查询id=1的学历
    //    GET, http://localhost:8080/education.ctl, 查询所有的学历
    /**
     * get 查找
     */
    /**
     * POST,http://localhost:8080/contract.ctl
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
        String education_json = JSONUtil.getJSON(request);
        //将JSON字串解析为education对象
       Education educationToAdd = JSON.parseObject(education_json, Education.class);
        //前台没有为id赋值，此处模拟自动生成id。Dao能实现数据库操作时，应删除此语句。
        //educationToAdd.setId(4 + (int)(Math.random()*100));
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加education对象
            EducationService.getInstance().add(educationToAdd);
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
     * DELETE, http://localhost:8080/contract.ctl?id=1
     * 删除一个部门对象：根据来自前端请求的id，删除数据库表中id的对应记录
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
            //到数据库表中删除对应的学历
            boolean deleted = EducationService.getInstance().delete(id);
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
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    /**
     * PUT, http://localhost:8080/contract.ctl
     * 修改一个部门对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String education_json = JSONUtil.getJSON(request);
        //将JSON字串解析为education对象
        Education educationToAdd = JSON.parseObject(education_json, Education.class);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //修改education对象
            boolean updated = EducationService.getInstance().update(educationToAdd);
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
        }
        //响应message到前端
        response.getWriter().println(message);
    }
    /**
     * GET, http://localhost:8080/education.ctl?id=1, 查询id=1的学历
     * GET, http://localhost:8080/education.ctl, 查询所有的学历
     * 响应一个或所有学历对象
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
            //如果id = null, 表示响应所有部门对象，否则响应id指定的学历对象
            if (id_str == null) {
                responseEducations(response);
            }else {
                int id = Integer.parseInt(id_str);
                responseEducation(id, response);
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
    //响应一个学历对象
    private void responseEducation(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找学历
        Education education = EducationService.getInstance().find(id);
        String education_json = JSON.toJSONString(education);
        //响应message到前端
        response.getWriter().println(education_json);
    }
    //响应所有学历对象
    private void responseEducations(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有学历
        Collection<Education> educations = EducationService.getInstance().findAll();
        String educations_json = JSON.toJSONString(educations, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(educations_json);
    }
}
