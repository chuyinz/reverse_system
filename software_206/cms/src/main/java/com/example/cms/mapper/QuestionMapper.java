package com.example.cms.mapper;

import com.example.cms.entity.Question;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Key;
import java.util.List;
/**
 * @author xzy
 * @date 2018/12/13
 */
public interface QuestionMapper {

    /**
     * 插入
     * @param question
     * @return
     */
    @Insert({"INSERT INTO question(klass_seminar_id,attendance_id,team_id,student_id,is_selected) VALUES (#{klassSeminarId},#{attendanceId},#{teamId},#{studentId},#{isSelected})"})
    @Results({
            @Result(property = "klassSeminarId", column = "klass_seminar_id"),
            @Result(property = "attendanceId", column = "attendance_id"),
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "isSelected", column = "isSelected")
    })
    void create(Question question);

    /**
     * 查找问题
     * @param id
     * @return id
     */
    @Select({"SELECT * FROM question WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "klassSeminarId", column = "klass_seminar_id"),
            @Result(property = "attendanceId", column = "attendance_id"),
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "isSelected", column = "isSelected")
    })
    Question getQuestion(BigInteger id);

    /**
     * 查找问题id
     * @param question
     * @return id
     */
    @Select({"SELECT id FROM question WHERE klass_seminar_id=#{klassSeminarId} AND attendance_id=#{attendanceId} AND team_id=#{teamId} AND student_id=#{studentId}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "klassSeminarId", column = "klass_seminar_id"),
            @Result(property = "attendanceId", column = "attendance_id"),
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "isSelected", column = "isSelected")
    })
    BigInteger getId(Question question);

    /**
     * 查找问题List
     * @param kSId
     * @return id
     */
    @Select({"SELECT * FROM question WHERE klass_seminar_id=#{klassSeminarId}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "klassSeminarId", column = "klassSeminar_id"),
            @Result(property = "attendanceId", column = "attendance_id"),
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "isSelected", column = "isSelected"),
            @Result(property = "score", column = "score")
    })
    List<Question> getQuestionList(BigInteger kSId);

    /**
     * 更新成绩
     * @param id
     * @param score
     */
    @Update({"UPDATE question SET score=#{score} WHERE id=#{id}"})
    void updateScore(@Param("id")BigInteger id, @Param("score")BigDecimal score);


    /**
     * 查找小组问题List
     * @param question
     * @return id
     */
    @Select({"SELECT * FROM question WHERE klass_seminar_id=#{klassSeminarId} AND team_id=#{teamId}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "klassSeminarId", column = "klassSeminar_id"),
            @Result(property = "attendanceId", column = "attendance_id"),
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "isSelected", column = "isSelected"),
            @Result(property = "score", column = "score")
    })
    List<Question> getTeamQuestionList(Question question);

}
