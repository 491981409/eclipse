package com.xxx.wxi.domain.base;

public class Agent {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dtwx_agent.da_id
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    private Integer daId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dtwx_agent.app_name
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    private String appName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dtwx_agent.app_type
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    private String appType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dtwx_agent.app_request_type
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    private String appRequestType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dtwx_agent.da_id
     *
     * @return the value of dtwx_agent.da_id
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public Integer getDaId() {
        return daId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dtwx_agent.da_id
     *
     * @param daId the value for dtwx_agent.da_id
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public void setDaId(Integer daId) {
        this.daId = daId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dtwx_agent.app_name
     *
     * @return the value of dtwx_agent.app_name
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dtwx_agent.app_name
     *
     * @param appName the value for dtwx_agent.app_name
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dtwx_agent.app_type
     *
     * @return the value of dtwx_agent.app_type
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public String getAppType() {
        return appType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dtwx_agent.app_type
     *
     * @param appType the value for dtwx_agent.app_type
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dtwx_agent.app_request_type
     *
     * @return the value of dtwx_agent.app_request_type
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public String getAppRequestType() {
        return appRequestType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dtwx_agent.app_request_type
     *
     * @param appRequestType the value for dtwx_agent.app_request_type
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    public void setAppRequestType(String appRequestType) {
        this.appRequestType = appRequestType == null ? null : appRequestType.trim();
    }
}