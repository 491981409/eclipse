package com.xxx.mapper.ticket;

import java.util.List;
import java.util.Map;

import com.xxx.domain.ticket.Ticket;
import com.xxx.util.sql.Criteria;

public interface TicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int insert(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int insertSelective(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    Ticket selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int updateByPrimaryKeySelective(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Wed Aug 09 14:40:40 CST 2017
     */
    int updateByPrimaryKey(Ticket record);
    
    
    List<Ticket> query(Criteria criteria);
    
    List<Map<String,Object>> queryTicket(Criteria criteria);
}