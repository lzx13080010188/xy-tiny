package com.xr.tiny.common.log;

import com.xr.tiny.common.uitl.IpAdrressUtil;
import com.xr.tiny.common.uitl.JacksonUtil;
import com.xr.tiny.modules.log.model.SysLog;
import com.xr.tiny.modules.log.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/14 下午6:13
 * @description : log横切面，spring通过动态代理加载对象
 */
@Component
@Aspect
public class LogAspect {


    @Autowired
    private SysLogService sysLogService;


    /**
     * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
     *
     * @param
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.xr.tiny.common.log.Log)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //保存日志
        SysLogVo sysLog = new SysLogVo();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();


        //获取操作
        Log operation = method.getAnnotation(Log.class);
        if (operation != null) {
            String value = operation.operateType();
            //保存获取的操作
            sysLog.setPeration(value);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();

        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //获取请求参数中携带的用户id或username存入日志库中
        String s = JacksonUtil.obj2json(args);
        String[] split = s.split("\\[");
        String[] split1 = split[1].split("\\]");
        Map<String, Object> map = JacksonUtil.json2map(split1[0]);
        if (map.get("id") != null) {
            sysLog.setUserId(Integer.parseInt(map.get("id").toString()));
        } else if (map.get("registerName") != null) {
            sysLog.setUserName(map.get("registerName").toString());
        }
        //将参数所在的数组转换成json
        String params = null;
        try {
            params = JacksonUtil.obj2json(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sysLog.setParams(params);

        //请求的时间
        sysLog.setCreateDate(new Date());

        //获取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            sysLog.setUserName(authentication.getName());
        }

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            sysLog.setOperateResult("请求正常");
        } catch (Throwable e) {
            e.printStackTrace();
            sysLog.setOperateResult("请求失败");
            sysLog.setAbnormity(e.toString());
        }

        //获取用户ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        sysLog.setIp(IpAdrressUtil.getIpAdrress(request));


        //调用service保存SysLog实体类到数据库
        SysLog model=new SysLog();
        BeanUtils.copyProperties(sysLog,model);
        sysLogService.save(model);
        return null;
    }
}
