package com.exercise.zhxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exercise.zhxy.pojo.Admin;
import com.exercise.zhxy.pojo.LoginParam;
import com.exercise.zhxy.pojo.Student;
import com.exercise.zhxy.pojo.Teacher;
import com.exercise.zhxy.service.AdminService;
import com.exercise.zhxy.service.StudentService;
import com.exercise.zhxy.service.TeacherService;
import com.exercise.zhxy.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Api(tags = "系统控制器")
@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取图片上的验证码
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        //将验证码文本放入session域，为下一次验证做准备
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        //将验证码图片相应给浏览器
        try {
            //将验证码图片通过输出流做出响应
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("登录请求验证")
    @PostMapping ("/login")
    public Result login(@RequestBody LoginParam loginParam,HttpServletRequest request){
        //获取用户提交的验证码和session域中的验证码
        HttpSession session = request.getSession();
        String sessionVerifiCode = (String) session.getAttribute("verifiCode");
        String loginVerifiCode = loginParam.getVerifiCode();
        if ("".equals(sessionVerifiCode) || null == sessionVerifiCode){
            //session过期,验证码超时
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        if (!sessionVerifiCode.equalsIgnoreCase(loginVerifiCode)){
            //验证码有误
            return Result.fail().message("验证码有误，请重新输入后重试");
        }
        //验证码使用完毕,移除当前请求域中的验证码
        session.removeAttribute("verifiCode");

        //准备一个Map用户存放响应的数据
        Map<String,Object> map = new LinkedHashMap<>();
        //分用户类型进行校验
        switch (loginParam.getUserType()){
            case 1:
                try {
                    Admin admin = adminService.login(loginParam);
                    if (null != admin){
                        //将用户的类型和用户的id转换成一个密文，以token反馈给客户端
                        String token = JwtHelper.createToken(admin.getId().longValue(), 1);
                        map.put("token",token);
                    }
                    else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student student = studentService.login(loginParam);
                    if (null != student){
                        //将用户的类型和用户的id转换成一个密文，以token反馈给客户端
                        String token = JwtHelper.createToken(student.getId().longValue(), 2);
                        map.put("token",token);
                    }
                    else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher teacher = teacherService.login(loginParam);
                    if (null != teacher){
                        //将用户的类型和用户的id转换成一个密文，以token反馈给客户端
                        String token = JwtHelper.createToken(teacher.getId().longValue(), 3);
                        map.put("token",token);
                    }
                    else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("查无此用户");
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("/getInfo")
    public Result getUserInfoByToken(@RequestHeader("token") String token){
        //获取用户中请求的token
        //检查token是否过期 20H
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration){
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //解析token,获取用户id和用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        //准备一个Map集合用于存响应的数据
        Map<String,Object> userMap = new HashMap<>();

        switch (userType){
            case 1:
                Admin admin = adminService.getAdminById(userId);
                userMap.put("userType",1);
                userMap.put("user",admin);
                break;
            case 2:
                Student student = studentService.getAdminById(userId);
                userMap.put("userType",2);
                userMap.put("user",student);
                break;
            case 3:
                Teacher teacher = teacherService.getAdminById(userId);
                userMap.put("userType",3);
                userMap.put("user",teacher);
                break;
        }
        return Result.ok(userMap);
    }

    @ApiOperation("图片上传统一入口")
    @PostMapping("/headerImgUpload")
    public Result imgUpload(@ApiParam("图像文件") @RequestPart("multipartFile") MultipartFile multipartFile){
        //使用UUID随机生成文件名
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //生成新的文件名字
        String newFileName = uuid.concat(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        //生成文件的保存路径(实际生产环境这里会使用真正的文件存储服务器)
        String portraitPath ="/Users/liuwq/Documents/IDEA/Zhxy/target/classes/public/upload/".concat(newFileName);
        //保存文件
        try {
            multipartFile.transferTo(new File(portraitPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //响应图片路径
        String path = "upload/".concat(newFileName);
        return Result.ok(path);
    }

    @ApiOperation("更新用户密码")
    @PostMapping("/updatePwd/{oldPwd}/{newPwd}")
    public Result updatePasswprd(
            @ApiParam("token口令") @RequestHeader("token") String token,
            @ApiParam("旧密码") @PathVariable("oldPwd") String oldPwd,
            @ApiParam("新密码") @PathVariable("newPwd") String newPwd){
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration){
            //token过期
            return Result.fail().message("token失效，请重新登陆后修改密码");
        }
        //通过token获取当前登录的用户id
        Long userId = JwtHelper.getUserId(token);
        //通过token获取当前登录的用户类型
        Integer userType = JwtHelper.getUserType(token);

        // 将明文密码转换为暗文
        oldPwd = MD5.encrypt(oldPwd);
        newPwd = MD5.encrypt(newPwd);

        switch (userType){
            case 1:
                QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",userId.intValue())
                                .eq("password",oldPwd);
                Admin admin = adminService.getOne(queryWrapper);
                if (admin != null){
                    //修改密码
                    admin.setPassword(newPwd);
                    adminService.saveOrUpdate(admin);
                }else {
                    return Result.fail().message("原密码有误!");
                }
                break;
            case 2:
                QueryWrapper<Student> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("id",userId.intValue())
                        .eq("password",oldPwd);
                Student student = studentService.getOne(queryWrapper2);
                if (student != null){
                    //修改密码
                    student.setPassword(newPwd);
                    studentService.saveOrUpdate(student);
                }else {
                    return Result.fail().message("原密码有误!");
                }
                break;
            case 3:
                QueryWrapper<Teacher> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("id",userId.intValue())
                        .eq("password",oldPwd);
                Teacher teacher = teacherService.getOne(queryWrapper3);
                if (teacher != null){
                    //修改密码
                    teacher.setPassword(newPwd);
                    teacherService.saveOrUpdate(teacher);
                }else {
                    return Result.fail().message("原密码有误!");
                }
                break;
        }
        return Result.ok();
    }

}
