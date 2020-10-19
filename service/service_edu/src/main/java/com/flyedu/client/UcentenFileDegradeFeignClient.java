package com.flyedu.client;

import com.flyedu.common.Result;
import com.flyedu.entity.vo.frontvo.UcentenMemberVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
