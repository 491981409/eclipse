package com.xxx.wxi.mapper.base;

import com.xxx.wxi.domain.base.Agent;

public interface AgentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dtwx_agent
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    int deleteByPrimaryKey(Integer daId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dtwx_agent
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    int insert(Agent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dtwx_agent
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    int insertSelective(Agent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dtwx_agent
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    Agent selectByPrimaryKey(Integer daId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dtwx_agent
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    int updateByPrimaryKeySelective(Agent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dtwx_agent
     *
     * @mbggenerated Tue Aug 29 09:39:59 CST 2017
     */
    int updateByPrimaryKey(Agent record);
}