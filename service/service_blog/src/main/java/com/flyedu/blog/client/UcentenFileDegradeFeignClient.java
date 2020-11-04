package com.flyedu.blog.client;

import com.flyedu.commonvo.UcentenMemberVo;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName UcentenFileDegradeFeignClient
 * @Author cai feifei
 * @date 2020.10.19 19:15
 * @Version
 */
@Component
public class UcentenFileDegradeFeignClient implements UcentenClient{
    @Override
    public UcentenMemberVo getUserInfoForCom(String id) {
        return null;
    }
}
