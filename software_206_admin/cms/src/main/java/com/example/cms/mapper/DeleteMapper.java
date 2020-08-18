package com.example.cms.mapper;

import org.apache.ibatis.annotations.Delete;

import java.math.BigInteger;

public interface DeleteMapper {

    /**
     * 删除教师课程
     * @param teacherId
     */
    @Delete({"DELETE FROM course WHERE teacher_id=#{teacherId}"})
    void deleteCourseByTeacherId(BigInteger teacherId);

    /**
     * 删除教师课程
     * @param teacherId
     */
    @Delete({"DELETE FROM team_valid_application WHERE teacher_id=#{teacherId}"})
    void deleteTeamValidApplicationByTeacherId(BigInteger teacherId);

    /**
     * 删除教师课程
     * @param teamId
     */
    @Delete({"DELETE FROM team_valid_application WHERE team_id=#{teamId}"})
    void deleteTeamValidApplicationByTeamId(BigInteger teamId);

    /**
     * 删除班级
     * @param courseId
     */
    @Delete({"DELETE FROM klass WHERE course_id=#{courseId}"})
    void deleteKlassByCourseId(BigInteger courseId);

    /**
     * 删除队伍
     * @param klassId
     */
    @Delete({"DELETE FROM team WHERE klass_id=#{klassId}"})
    void deleteTeamByKlassId(BigInteger klassId);

    /**
     * 删除展示
     * @param kSId
     */
    @Delete({"DELETE FROM attendance WHERE klass_seminar_id=#{klassSeminarId}"})
    void deleteAttendanceByKSId(BigInteger kSId);

    /**
     * 删除学生组队关系
     * @param teamId
     */
    @Delete({"DELETE FROM team_student WHERE team_id=#{teamId}"})
    void deleteTeamStudentByTeamId(BigInteger teamId);

    /**
     * 删除course_member_limit_strategy
     * @param courseId
     */
    @Delete({"DELETE FROM course_member_limit_strategy WHERE course_id=#{courseId}"})
    void deleteCourseMemberLimitStrategy(BigInteger courseId);

    /**
     * 删除轮次
     * @param courseId
     */
    @Delete({"DELETE FROM round WHERE course_id=#{courseId}"})
    void deleteRoundByCourseId(BigInteger courseId);

    /**
     * 删除讨论课
     * @param courseId
     */
    @Delete({"DELETE FROM seminar WHERE course_id=#{courseId}"})
    void deleteSeminarByCourseId(BigInteger courseId);

    /**
     * 删除组队分享
     * @param courseId
     */
    @Delete({"DELETE FROM share_team_application WHERE main_course_id=#{courseId} OR sub_course_id=#{courseId}"})
    void deleteShareTeamApplicationByCourseId(BigInteger courseId);

    /**
     * 删除组队策略
     * @param courseId
     */
    @Delete({"DELETE FROM team_strategy WHERE course_id=#{courseId}"})
    void deleteTeamStrategyByCourseId(BigInteger courseId);

    /**
     * 删除klass_round
     * @param klassId
     */
    @Delete({"DELETE FROM klass_round WHERE klass_id=#{klassId}"})
    void deleteKlassRoundByKlassId(BigInteger klassId);

    /**
     * 删除klass_seminar
     * @param klassId
     */
    @Delete({"DELETE FROM klass_seminar WHERE klass_id=#{klassId}"})
    void deleteKlassSeminarByKlassId(BigInteger klassId);

    /**
     * 删除klass_student
     * @param klassId
     */
    @Delete({"DELETE FROM klass_student WHERE klass_id=#{klassId}"})
    void deleteKlassStudentByKlassId(BigInteger klassId);

    /**
     * 删除klass_team
     * @param klassId
     */
    @Delete({"DELETE FROM klass_team WHERE klass_id=#{klassId}"})
    void deleteKlassTeamByKlassId(BigInteger klassId);

    /**
     * 删除question
     * @param klassSeminarId
     */
    @Delete({"DELETE FROM question WHERE klass_seminar_id=#{klassSeminarId}"})
    void deleteQuestionByKlassSeminarId(BigInteger klassSeminarId);

    /**
     * 删除seminar_score
     * @param klassSeminarId
     */
    @Delete({"DELETE FROM seminar_score WHERE klass_seminar_id=#{klassSeminarId}"})
    void deleteSeminarScoreByKlassSeminarId(BigInteger klassSeminarId);

    /**
     * 删除seminar_score
     * @param roundId
     */
    @Delete({"DELETE FROM round_score WHERE round_id=#{roundId}"})
    void deleteRoundScoreByRoundId(BigInteger roundId);
}
