package com.xxx.domain.ticket;

import java.util.Date;

public class TicketStateType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.id
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private Short id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.name
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.comments
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private String comments;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.create_time
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.create_by
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private Integer createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.change_time
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private Date changeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket_state_type.change_by
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    private Integer changeBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.id
     *
     * @return the value of ticket_state_type.id
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public Short getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.id
     *
     * @param id the value for ticket_state_type.id
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.name
     *
     * @return the value of ticket_state_type.name
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.name
     *
     * @param name the value for ticket_state_type.name
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.comments
     *
     * @return the value of ticket_state_type.comments
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.comments
     *
     * @param comments the value for ticket_state_type.comments
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.create_time
     *
     * @return the value of ticket_state_type.create_time
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.create_time
     *
     * @param createTime the value for ticket_state_type.create_time
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.create_by
     *
     * @return the value of ticket_state_type.create_by
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.create_by
     *
     * @param createBy the value for ticket_state_type.create_by
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.change_time
     *
     * @return the value of ticket_state_type.change_time
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.change_time
     *
     * @param changeTime the value for ticket_state_type.change_time
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket_state_type.change_by
     *
     * @return the value of ticket_state_type.change_by
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public Integer getChangeBy() {
        return changeBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket_state_type.change_by
     *
     * @param changeBy the value for ticket_state_type.change_by
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    public void setChangeBy(Integer changeBy) {
        this.changeBy = changeBy;
    }
}