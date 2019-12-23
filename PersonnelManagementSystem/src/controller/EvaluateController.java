package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Evaluate;
import service.EvaluateService;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/evaluate.ctl")
public class EvaluateController extends HttpServlet {
    //    GET, http://localhost:8080/evaluate.ctl?id=1, 查询id=1的考核
    //    GET, http://localhost:8080/evaluate.ctl, 查询所有的考核
    //    POST, http://localhost:8080/evaluate.ctl, 增加考核
    //    PUT, http://localhost:8080/evaluate.ctl, 修改考核
    //    DELETE, http://localhost:8080/evaluate.ctl?id=1, 删除id=1的考核
    /**
     * 方法-功能
     * put 修改
     * post 添加
     * delete 删除
     * get 查找
     */
    /**
     * POST,http://localhost:8080/evaluate.ctl
     * 增加一个考核对象：将来自前端请求的JSON对象，增加到数据库表中
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据request对象，获得代表参数的JSON字串
        String evaluate_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Evaluate对象
        Evaluate evaluateToAdd = JSON.parseObject(evaluate_json, Evaluate.class);
        //前台没有为id赋值，此处模拟自动生成id。Dao能实现数据库操作时，应删除此语句。
        evaluateToAdd.setId(4 + (int)(Math.random()*100));
        //响应
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Evaluate对象
            EvaluateService.getInstance().add(evaluateToAdd);
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
     * DELETE, http://localhost:8080/evaluate.ctl?id=1
     * 删除一个考核对象：根据来自前端请求的id，删除数据库表中id的对应记录
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
            //到数据库表中删除对应的考核
            EvaluateService.getInstance().delete(id);
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
     * PUT, http://localhost:8080/evaluate.ctl
     * 修改一个考核对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String evaluate_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Evaluate对象
        Evaluate evaluateToAdd = JSON.parseObject(evaluate_json, Evaluate.class);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //增加Evaluate对象
            EvaluateService.getInstance().update(evaluateToAdd);
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
        String id_str = request.getParameter("id");
        String staff_str = request.getParameter("staffId");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //如果id = null, 表示响应所有考核对象，否则响应id指定的考核对象
            if (id_str == null&&staff_str==null) {
                responseEvaluates(response);
            }else {
                int id = Integer.parseInt(id_str);
                if(staff_str==null) {
                    responseEvaluate(id, response);
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
    //响应一个考核对象
    private void responseEvaluate(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //根据id查找考核
        Evaluate evaluate = EvaluateService.getInstance().find(id);
        String evaluate_json = JSON.toJSONString(evaluate);
        //响应message到前端
        response.getWriter().println(evaluate_json);
    }
    //响应所有考核对象
    private void responseEvaluates(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有考核
        Collection<Evaluate> evaluates = EvaluateService.getInstance().findAll();
        String evaluates_json = JSON.toJSONString(evaluates, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(evaluates_json);
    }
    //响应staff_id的所有考核对象
    private void responseDepSch(int staff_id,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有考核
        Collection<Evaluate> evaluates = EvaluateService.getInstance().findAllByStaff(staff_id);
        String evaluates_json = JSON.toJSONString(evaluates, SerializerFeature.DisableCircularReferenceDetect);
        //响应message到前端
        response.getWriter().println(evaluates_json);
    }
}

