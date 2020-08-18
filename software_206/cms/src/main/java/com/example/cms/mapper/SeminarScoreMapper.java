package com.example.cms.mapper;

import com.example.cms.entity.SeminarScore;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018/12/13
 */
public interface SeminarScoreMapper {
    /**
     * a
     * @param seminarScore
     */
    @Insert({"INSERT INTO seminar_score(klass_seminar_id,team_id,total_score,presentation_score,question_score,report_score)" +
            " VALUES(#{klassSeminarId},#{teamId},#{totalScore},#{presentationScore},#{questionScore},#{reportScore})"})
    @Results({
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    void create(SeminarScore seminarScore);

    /**
     * a
     * @param klassSeminarId
     * @param teamId
     * @return
     */
    @Select({"SELECT * FROM seminar_score WHERE klass_seminar_id=#{klassSeminarId} AND team_id=#{teamId}"})
    @Results({
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    SeminarScore getSeminarScore(@Param("klassSeminarId") BigInteger klassSeminarId,@Param("teamId") BigInteger teamId);

    /**
     * a
     * @param seminarScore
     */
    @Update({"UPDATE seminar_score SET total_score=#{totalScore},presentation_score=#{presentationScore},question_score=#{questionScore},report_score=#{reportScore} WHERE klass_seminar_id=#{klassSeminarId} AND team_id=#{teamId}"})
    @Results({
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    void update(SeminarScore seminarScore);


    /**
     * 删除
     * @param seminarScore
     */
    @Delete({"DELETE FROM seminar_score WHERE klass_seminar_id=#{klassSeminarId} AND team_id=#{teamId}"})
    void delete(SeminarScore seminarScore);

    /**
     * a
     * @param teamId
     * @return
     */
    @Select({"SELECT klass_seminar_id FROM seminar_score WHERE team_id=#{teamId}"})
    List<BigInteger> getKlassSeminarList(@Param("teamId") BigInteger teamId);
}