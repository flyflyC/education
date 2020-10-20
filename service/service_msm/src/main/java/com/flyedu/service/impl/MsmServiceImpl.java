package com.flyedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.flyedu.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @Description
 * @ClassName MsmServiceImpl
 * @Author cai feifei
 * @date 2020.10.17 16:42
 * @Version
 */
@Service
public class MsmServiceImpl implements MsmService {

    /**
     * 发送短信
     * @param map
     * @param phone
     * @return
     */
    @Override
    public boolean send(HashMap<String, Object> map, String phone) {
        if(StringUtils.isEmpty(phone)) {return false;}

        DefaultProfile profile =
                DefaultProfile.getProfile("cn-hangzhou", "accessKeyId", "secret");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        //设置发送相关的参数
        //手机号
        request.putQueryParameter("PhoneNumbers",phone);
        //申请阿里云 签名名称
        request.putQueryParameter("SignName","我的微教育在线教育网站");
        //申请阿里云 模板code
        request.putQueryParameter("TemplateCode","SMS_204756418");
        //验证码数据，转换json数据传递
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        }catch(Exception e) {
        e.printStackTrace();
        }
        return false;
    }
}
