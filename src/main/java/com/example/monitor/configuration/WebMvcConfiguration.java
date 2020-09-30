package com.example.monitor.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.monitor.DTO.LoginDTO;
import com.example.monitor.DTO.UserLoginDTO;
import com.example.monitor.annotation.Auth;

import com.example.monitor.core.cache.IDoCache;
import com.example.monitor.core.result.Result;
import com.example.monitor.core.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    IDoCache cache;

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);
    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(converter);
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，
        if (!"dev".equals(env)) { //开发环境忽略签名认证
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    String requestURI = request.getRequestURI();
                    System.out.println("请求地址"+ requestURI);
                    /*if ("/file".equals(requestURI.substring(0,4))){
                        return true;
                    }*/


                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;

                        // 获取方法上的注解
                        Auth authAnno = handlerMethod.getMethod().getAnnotation(Auth.class);
                        // 如果方法上的注解为空 则获取类的注解
                        if (authAnno == null) {
                            authAnno = handlerMethod.getBean().getClass().getAnnotation(Auth.class);
                        }

                        // 如果标记了注解，则判断权限
                        if (authAnno != null) {
                            String[] authGroup = authAnno.value();
                            if (Arrays.asList(authGroup).contains("noauth"))
                                return true;

                            if (authGroup != null && authGroup.length > 0) {
                                String auth = request.getHeader("Authorization");
                                if (auth == null) {//没定义Authorization不让调用
                                    logger.warn("没定义Authorization不让调用，请求接口：{}，请求IP：{}，请求参数：{}",
                                            request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                                    Result result = ResultUtil.fail("Authorization缺失");
                                    responseResult(response, result);
                                    return false;
                                }
                                if (auth.isEmpty()) {//定义了Authorization但是为空不行
                                    logger.warn("定义了Authorization但是为空不行，请求接口：{}，请求IP：{}，请求参数：{}",
                                            request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                                    Result result = ResultUtil.fail("Authorization为空");
                                    responseResult(response, result);
                                    return false;
                                }

                                if (!(auth.startsWith("token") || auth.startsWith("sign"))) {//必须是token 或sign 开头
                                    Result result = ResultUtil.fail("Authorization必须是token或sign开头");
                                    responseResult(response, result);
                                    return false;
                                }

                                if (!auth.contains(" ")) {
                                    Result result = ResultUtil.fail("Authorization格式非法");
                                    responseResult(response, result);
                                    return false;
                                }

                                String[] auth_array = auth.split(" ");
                                if ("token".equals(auth_array[0])) {
                                    String accessToken = auth_array[1];
                                    JSONObject entity = cache.getData(accessToken);
                                    if (entity != null) {//内存里如果有，认证通过
                                        //时间延续
                                        cache.setData(accessToken, entity, 24 * 60 * 60);
                                        if (!checkPermission(entity, "")) {
                                            Result result = ResultUtil.fail("当前操作无权限，请联系管理员");
                                            responseResult(response, result);
                                            return false;
                                        }
//
                                    } else {
                                        logger.warn("无效令牌，请求接口：{}，请求IP：{}，请求参数：{}",
                                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                                        Result result = ResultUtil.fail("无效令牌");
                                        responseResult(response, result);
                                        return false;
                                    }
                                } else if ("sign".equals(auth_array[0])) {
                                    //TODO
                                    logger.warn("暂不支持sign，请求接口：{}，请求IP：{}，请求参数：{}",
                                            request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                                    Result result = ResultUtil.fail("暂不支持sign");
                                    responseResult(response, result);
                                    return false;
                                }
                            }
                        }
                    }
                    // 根据请求路径判断当前角色是否有权限
                    String auth = request.getHeader("Authorization");
                    if (auth != null){

                        String[] auth_array = auth.split(" ");
                        JSONObject data = cache.getData(auth_array[1]);
                        UserLoginDTO dto = data.toJavaObject(UserLoginDTO.class);
                        List<String> permissionURLList = dto.getPermissionURLList();
                        permissionURLList.forEach(System.out::println);
                        if (!permissionURLList.contains(requestURI)) {
                            Result result = ResultUtil.fail("权限不足");
                            responseResult(response, result);
                            return false;
                        }
                    }
                    return true;
                }
            });
        }
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

    /**
     * 判断接口权限
     * @param entity
     * @param expression
     * @return
     */
    private boolean checkPermission(JSONObject entity, String expression) {
        if (expression.equals(""))
            return true;
        LoginDTO loginDTO = JSON.toJavaObject(entity, LoginDTO.class);
        //TODO 判断当前接口权限
        return false;
    }


}
