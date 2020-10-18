package com.flyedu.controller;

import com.flyedu.common.JwtUtils;
import com.flyedu.entity.UcenterMember;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.UcenterMemberService;
import com.flyedu.utils.ConstantWxUtils;

import com.flyedu.utils.HttpClientUtils;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;


/**
 * @Description 只是请求地址，不需要返回数据
 * @ClassName WxApiController
 * @Author cai feifei
 * @date 2020.10.18 17:41
 * @Version
 */
@Api(description = "微信登录")
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * 2 获取扫描人信息，添加数据
     * @param code
     * @param state
     * @return
     */
    @GetMapping("callback")
    public String callback(String code, String state) {
        System.out.println(code);
        System.out.println(state);
        try {
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid; 使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            System.out.println(accessTokenInfo);

            //从accessTokenInfo字符串获取出来两个值 access_token 和 openid
            //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            //使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessTokenInfo = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessTokenInfo.get("access_token");
            String  openid = (String) mapAccessTokenInfo.get("openid");


            //判断数据库中是否存在该用户
            UcenterMember member = memberService.getMemberByOpenid(openid);

            if (member==null){
                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);

                System.out.println("userInfo："+userInfo);

                //获取用户信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimgurl = (String) userInfoMap.get("headimgurl");

                member = new UcenterMember();
                member.setAvatar(headimgurl);
                member.setNickname(nickname);
                member.setOpenid(openid);
                boolean save = memberService.save(member);
                System.out.println("sava==="+save);
                if (!save){
                    throw new EduException(20001,"微信用户注册失败！");
                }
            }
            System.out.println(member);
            member=memberService.getMemberByOpenid(openid);
            String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

            return "redirect:http://localhost:3000?token="+token;
        }catch (Exception e){
            throw new EduException(20001,"登录失败");
        }

    }

    /**
     * 1 生成微信扫描二维码
     * @return
     */
    @GetMapping("login")
    public String getWxCode() {
        //固定地址，后面拼接参数
//        String url = "https://open.weixin.qq.com/" +
//                "connect/qrconnect?appid="+ ConstantWxUtils.WX_OPEN_APP_ID+"&response_type=code";

        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        }catch(Exception e) {
            e.fillInStackTrace();
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "V-Edu"
        );

        //重定向到请求微信地址里面
        return "redirect:"+url;
    }
}
