package com.example.cms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018/12/13
 */
public interface MemberLimitStrategyMapper {
    /**
     * 根据课程人数限制获得id
     * @param memberMin
     * @param memberMax
     * @return
     */
    @Select({"SELECT id FROM member_limit_strategy WHERE min_member=#{memberMin} and max_member=#{memberMax} "})
    BigInteger getIdByMemberLimit(@Param("memberMin") int memberMin,@Param("memberMax")int memberMax);

    /**
     * 增加小组人数限制
     * @param memberMin
     * @param memberMax
     */
    @Insert({"INSERT INTO member_limit_strategy(min_member,max_member) VALUES(#{memberMin},#{memberMax})" })
    void createMemberLimit(@Param("memberMin") int memberMin,@Param("memberMax")int memberMax);


    /**
     * 设置组队人数上下限
     * @param minMember 人数下限
     * @param maxMember 人数上限
     */
    void creatMemberLimit(int minMember,int maxMember);

    /**
     *获得人数上限
     * @param id
     * @return
     */
    @Select({"SELECT max_member FROM member_limit_strategy WHERE id=#{id} "})
    Integer getMaxMemberById(@Param("id") BigInteger id);


    /**
     *获得人数下限
     * @param id
     * @return
     */
    @Select({"SELECT min_member FROM member_limit_strategy WHERE id=#{id} "})
    Integer getMinMemberById(@Param("id") BigInteger id);

}
