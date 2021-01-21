package com.shr.service.minio.service;

import com.shr.service.minio.entity.MinioEntity;
import com.shr.service.minio.entity.common.ConstantInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：206612
 * @date ：Created in 2021/1/19 13:47
 * @description：Test
 */
public class Test {
    public static void main(String[] args)
    {
        List<MinioEntity> result1 = new ArrayList<>();
        List<MinioEntity> result = new ArrayList<>();
        MinioEntity m1 = new MinioEntity();
        m1.setFileName("shr/Client01/2020/12/24/TASK_CODE_140/CCD/1.jpg");
        MinioEntity m2 = new MinioEntity();
        m2.setFileName("shr/Client01/2020/12/24/TASK_CODE_140/CCD/2.jpg");
        MinioEntity m3 = new MinioEntity();
        m3.setFileName("shr/Client01/2020/12/24/TASK_CODE_140/CCD/3.jpg");
        MinioEntity m4 = new MinioEntity();
        m4.setFileName("shr/Client01/2020/12/24/TASK_CODE_140/CCD/4.jpg");
        MinioEntity m5 = new MinioEntity();
        m5.setFileName("shr/Client01/2020/12/24/TASK_CODE_140/CCD/5.jpg");
        MinioEntity m6 = new MinioEntity();
        m6.setFileName("shr/Client01/2020/12/25/TASK_CODE_140/CCD/1.jpg");
        MinioEntity m7 = new MinioEntity();
        m7.setFileName("shr/Client01/2020/12/25/TASK_CODE_140/CCD/2.jpg");

        result.add(m1);
        result.add(m2);
        result.add(m3);
        result.add(m4);
        result.add(m5);
        result.add(m6);
        result.add(m7);

        String a = "shr/Client01/2020/12";
        System.out.println(a);

        String key = "[A-Za-z0-9.-]{3,}$";
        System.out.println(a.matches(key));
    }
}
