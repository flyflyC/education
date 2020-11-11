package com.flyedu.blog.service.impl;

import com.flyedu.blog.entity.Blog;
import com.flyedu.blog.mapper.BlogMapper;
import com.flyedu.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-30
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
}
