package com.example.cms.mapper;

import com.example.cms.entity.RoundScore;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018/12/13
 */
public interface RoundScoreMapper {
    /**
     * a
     * @param roundScore
     */
    @Insert({"INSERT INTO round_score(round_id,team_id,total_score,presentation_score,question_score,report_score)" +
            " VALUES(#{roundId},#{teamId},#{totalScore},#{presentationScore},#{questionScore},#{reportScore}"})
    @Results({
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    void create(RoundScore roundScore);

    /**
     * a
     * @param roundId
     * @param teamId
     * @return
     */
    @Select({"SELECT * FROM round_score WHERE round_id=#{roundId} AND team_id=#{teamId}"})
    @Results({
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    RoundScore getRoundScore(BigInteger roundId, BigInteger teamId);

    /**
     * a
     * @param roundId
     * @return
     */
    @Select({"SELECT * FROM round_score WHERE round_id=#{roundId}"})
    @Results({
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    List<RoundScore> getRoundScoreList(BigInteger roundId);

    /**
     * a
     * @param roundScore
     */
    @Update({"UPDATE round_score SET total_score=#{totalScore},presentation_score=#{presentationScore},question_score=#{questionScore},report_score=#{reportScore} WHERE round_id=#{roundId} AND team_id=#{teamId}"})
    void update(RoundScore roundScore);
}