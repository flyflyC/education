package com.flyedu.service.impl;

import com.flyedu.client.EduClient;
import com.flyedu.client.UcentenClient;
import com.flyedu.commonvo.CourseWebOrderVo;
import com.flyedu.commonvo.UcentenMemberVo;
import com.flyedu.entity.Order;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.OrderMapper;
import com.flyedu.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyedu.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UcentenClient ucentenClient;
    @Autowired
    private EduClient eduClient;
    /**
     * 根据课程id和用户id创建订单
     * @param courseId
     * @param memberId
     * @return
     */
    @Override
    public String creatOrder(String courseId, String memberId) {
        //远程调用，获取课程信息
        CourseWebOrderVo courseInfo = eduClient.getCourseInfoToOrder(courseId);
        //远程调用，获取用户信息
        UcentenMemberVo userInfo = ucentenClient.getUserInfoForCom(memberId);
        //创建oder对象，给order对象赋值
        Order order = new Order();
        //订单号
        order.setOrderNo(OrderNoUtil.getOrderNo());
        //课程id
        order.setCourseId(courseId);
        //课程标题
        order.setCourseTitle(courseInfo.getTitle());
        //课程封面
        order.setCourseCover(courseInfo.getCover());
        //讲师姓名
        order.setTeacherName(courseInfo.getTeacherName());
        //会员id
        order.setMemberId(memberId);
        //会员昵称
        order.setNickname(userInfo.getNickname());
        //会员手机号
        order.setMobile(userInfo.getMobile());
        //价格
        order.setTotalFee(courseInfo.getPrice());
        //支付类型（1：微信 2：支付宝）
        order.setPayType(1);
        //订单状态（0：未支付 1：已支付）
        order.setStatus(0);
        int insert = baseMapper.insert(order);
        if (insert>0){
            return order.getOrderNo();
        }else {
            throw new EduException(20001,"创建订单失败");
        }

    }
}
