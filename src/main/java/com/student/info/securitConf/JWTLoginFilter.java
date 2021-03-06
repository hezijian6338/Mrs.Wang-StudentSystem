package com.student.info.securitConf;

import com.student.info.DTO.TokenDTO;
import com.student.info.core.Result;
import com.student.info.core.ResultCode;
import com.student.info.model.User;
import com.student.info.service.UserService;
import com.student.info.service.impl.UserServiceImpl;
import com.student.info.utils.JWTUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * @author zhaoxinguo on 2017/9/12.
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new User();
            String password = req.getParameter("password");

            user.setUsername(req.getParameter("username"));
            // user.setPassword(req.getParameter("password"));

            user.setPassword(JWTUtils.setAuthentication(password));

            System.out.println("加密前的密码:" + password);
            System.out.println("加密后的密码:" + JWTUtils.setAuthentication(password));
//            User user = new ObjectMapper()
//                    .readValue(req.getInputStream(), User.class);

            SecurityContextHolder.setStrategyName(MODE_INHERITABLETHREADLOCAL);
            System.out.println("Check Strategy: " + SecurityContextHolder.getContextHolderStrategy().toString());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        UserService userService = new UserServiceImpl();

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        res.addHeader("X-Token", "Dragonsking " + token);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken("Dragonsking " + token);
        res.setStatus(200);
        Result result = new Result();
        result.setData(tokenDTO);
        result.setCode(ResultCode.SUCCESS).setMessage("登陆成功");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Content-type", "application/json;charset=UTF-8");
        res.getWriter().write(JSON.toJSONString(result));
        System.out.println("用户：" + ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername()
                + "登陆成功,Token：" + token);
    }
}