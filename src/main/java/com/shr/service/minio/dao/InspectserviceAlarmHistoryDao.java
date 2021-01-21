package com.shr.service.minio.dao;

import com.shr.service.minio.entity.InspectserviceAlarmHistoryEntity;

public interface InspectserviceAlarmHistoryDao {
    int deleteByPrimaryKey(String id);

    int insert(InspectserviceAlarmHistoryEntity record);

    int insertSelective(InspectserviceAlarmHistoryEntity record);

    InspectserviceAlarmHistoryEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InspectserviceAlarmHistoryEntity record);

    int updateByPrimaryKey(InspectserviceAlarmHistoryEntity record);
}