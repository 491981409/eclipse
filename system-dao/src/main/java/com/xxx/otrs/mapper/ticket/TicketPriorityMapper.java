package com.xxx.otrs.mapper.ticket;

import com.xxx.otrs.domain.ticket.TicketPriority;

public interface TicketPriorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_priority
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_priority
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int insert(TicketPriority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_priority
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int insertSelective(TicketPriority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_priority
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    TicketPriority selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_priority
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int updateByPrimaryKeySelective(TicketPriority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket_priority
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int updateByPrimaryKey(TicketPriority record);
}