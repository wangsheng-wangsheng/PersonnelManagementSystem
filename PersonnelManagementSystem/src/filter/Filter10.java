package filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@WebFilter(filterName = "Filter10",urlPatterns = {"/*"})
public class Filter10 implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //打印过滤器开始
        System.out.println("Filter 0 - encoding begins");
        //将请求和响应转换成http的请求。。
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取请求路径
        String path = request.getRequestURI();
        //获取请求的方法
        String method = request.getMethod();
        if (path.contains("kcsj") || path.contains("ts") || path.contains("html")){
            System.out.println("未设置字符编码格式");
        } else {
            System.out.println(method);
            //给剩下的请求设置响应编码格式为utf8
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("设置响应对象字符编码格式为utf8");
            //如果是添加和修改方法则设置请求编码格式
            if (method.equals("POST")||method.equals("PUT")){
                request.setCharacterEncoding("UTF-8");
                System.out.println("设置请求对象字符编码格式为utf8");
            }
        }
        //放行
        chain.doFilter(req,resp);
        System.out.println("Filter 0 - encoding ends");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}