package com.flyedu.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.flyedu.common.Result;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.service.VodService;
import com.flyedu.utils.InitVodObject;
import com.flyedu.utils.VodUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description
 * @ClassName VodServiceImpl
 * @Author cai feifei
 * @date 2020.10.15 15:11
 * @Version
 */
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    @Override
    public String uploadVideo(MultipartFile file)  {
        //文件原始名
        String fileName=file.getOriginalFilename();
        //上传之后显示名称
        String title=fileName.substring(0,fileName.lastIndexOf("."));

        String videoId = null;
        //文件流
        try {
            InputStream inputStream=file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(VodUtil.KEY_ID, VodUtil.KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //请求视频点播服务的请求ID
            System.out.print("RequestId=" + response.getRequestId() + "\n");

            if (response.isSuccess()) {
                videoId= response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId= response.getVideoId();
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
        }catch (IOException e){
            e.fillInStackTrace();
        }

        return videoId;
    }

    /**
     * 同时删除阿里云上的多个视频
     * @param videoList
     * @return
     */
    @Override
    public void deleteAlyVideoBach(List videoList) {
        String s = StringUtils.join(videoList, ",");
        try {
            //初始化视频点播对象
            DefaultAcsClient initVodClient = InitVodObject.initVodClient(VodUtil.KEY_ID, VodUtil.KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest videoRequest = new DeleteVideoRequest();
            //向request对象传入id
            videoRequest.setVideoIds(s);
            //调用初始化对象删除id
            initVodClient.getAcsResponse(videoRequest);
        }catch (Exception e){
            throw new EduException(20001,"删除失败");
        }

    }

}
