package com.wula.stumanage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Configuration
public class PicWebConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("配置文件已经生效");
        //关于图片上传后需要重启服务器才能刷新图片
        //这是一种保护机制，为了防止绝对路径被看出来，目录结构暴露
        //解决方法:将虚拟路径/images/
        //        向绝对路径 (/Users/linyi/Desktop/zcStuMange/serviceboot/src/main/resources/images/)映射

        String path="/Users/linyi/Desktop/zcStuMange/serviceboot/src/main/resources/images/";
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);


    }
}




