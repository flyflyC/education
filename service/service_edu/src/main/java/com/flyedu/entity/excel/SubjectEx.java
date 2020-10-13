package com.flyedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description
 * @ClassName SubjectEx
 * @Author cai feifei
 * @date 2020.10.13 16:37
 * @Version
 */
@Data
public class SubjectEx {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
