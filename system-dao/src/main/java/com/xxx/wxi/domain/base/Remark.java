package com.xxx.wxi.domain.base;

import java.util.Date;

public class Remark {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remark.r_id
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    private String rId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remark.r_content
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    private String rContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remark.create_time
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remark.create_by
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column remark.r_sort
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    private Integer rSort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remark.r_id
     *
     * @return the value of remark.r_id
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public String getrId() {
        return rId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remark.r_id
     *
     * @param rId the value for remark.r_id
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public void setrId(String rId) {
        this.rId = rId == null ? null : rId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remark.r_content
     *
     * @return the value of remark.r_content
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public String getrContent() {
        return rContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remark.r_content
     *
     * @param rContent the value for remark.r_content
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public void setrContent(String rContent) {
        this.rContent = rContent == null ? null : rContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remark.create_time
     *
     * @return the value of remark.create_time
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remark.create_time
     *
     * @param createTime the value for remark.create_time
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remark.create_by
     *
     * @return the value of remark.create_by
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remark.create_by
     *
     * @param createBy the value for remark.create_by
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column remark.r_sort
     *
     * @return the value of remark.r_sort
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public Integer getrSort() {
        return rSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column remark.r_sort
     *
     * @param rSort the value for remark.r_sort
     *
     * @mbggenerated Fri Oct 13 15:20:06 CST 2017
     */
    public void setrSort(Integer rSort) {
        this.rSort = rSort;
    }
}