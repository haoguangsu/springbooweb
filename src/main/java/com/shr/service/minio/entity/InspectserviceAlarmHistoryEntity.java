package com.shr.service.minio.entity;

/**
    * 巡视告警记录表
    */
public class InspectserviceAlarmHistoryEntity {
    /**
    * ID
    */
    private String id;

    /**
    * 间隔名称
    */
    private String deviceBay;

    /**
    * 设备名称
    */
    private String deviceName;

    /**
    * 部件名称
    */
    private String componentName;

    /**
    * 实物ID
    */
    private String objectId;

    /**
    * 缺陷类别
    */
    private String defectType;

    /**
    * 告警等级
    */
    private Integer level;

    /**
    * 缺陷或异常图像
    */
    private String imageUrl;

    /**
    * 摄像机ID
    */
    private String cameraId;

    /**
    * 预置位
    */
    private Integer presetToken;

    /**
    * 优先级
    */
    private Integer priority;

    /**
    * 审核者
    */
    private String auditor;

    /**
    * 告警事件组
    */
    private String eventGroupName;

    /**
    * 告警事件项
    */
    private String eventItemName;

    /**
    * 审核状态
    */
    private Boolean auditStatus;

    /**
    * 审核时间
    */
    private String auditTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceBay() {
        return deviceBay;
    }

    public void setDeviceBay(String deviceBay) {
        this.deviceBay = deviceBay;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getPresetToken() {
        return presetToken;
    }

    public void setPresetToken(Integer presetToken) {
        this.presetToken = presetToken;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getEventGroupName() {
        return eventGroupName;
    }

    public void setEventGroupName(String eventGroupName) {
        this.eventGroupName = eventGroupName;
    }

    public String getEventItemName() {
        return eventItemName;
    }

    public void setEventItemName(String eventItemName) {
        this.eventItemName = eventItemName;
    }

    public Boolean getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Boolean auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}