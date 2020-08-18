package com.example.cms.mapper;

import com.example.cms.entity.Klass;
import com.example.cms.entity.KlassRound;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018/12/13
 */
public interface KlassRoundMapper {

    /**
     * 创建班级轮次
     * @param klassId
     * @param roundId
     * @param enrollNumber
     */
    @Insert({"INSERT INTO klass_round(klass_id,round_id,enroll_number) VALUES(#{klassId},#{roundId},#{enrollNumber})"})
    void createKlassRound(BigInteger klassId,BigInteger roundId,Integer enrollNumber);


    /**
     * 更改
     * @param klassRound
     */
    @Update({"UPDATE klass_round SET enroll_number=#{enrollNumber} WHERE klass_id=#{klassId} AND round_id=#{roundId}"})
    void update(KlassRound klassRound);

    /**
     * 通过id获取轮次
     * @param roundId
     * @return
     */
    @Select({"SELECT * FROM klass_round WHERE round_id=#{roundId}"})
    List<KlassRound> getByRoundId(BigInteger roundId);

    /**
     *通过轮次id和班级id获取轮次
     * @param klassId
     * @param roundId
     * @return
     */
    @Select({"SELECT * FROM klass_round WHERE  klass_id=#{klassId} AND round_id=#{roundId}"})
    KlassRound getByKlassIdAndRoundId(BigInteger klassId, BigInteger roundId);

    /**
     * 删除轮次
     * @param klassId
     * @param roundId
     */
    @Delete({"DELETE FROM klass_round where klass_id=#{klassId} AND round_id=#{roundId}"})
    void deleteKlassRound(BigInteger klassId,BigInteger roundId);
}
