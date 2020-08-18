package com.example.cms.service;

import com.example.cms.dao.StudentDAO;
import com.example.cms.dao.TeacherDAO;
import com.example.cms.entity.User;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.DeleteMapper;
import com.example.cms.mapper.GetListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018/12/17
 */
@Service
public class AdminService {

    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private DeleteMapper deleteMapper;
    @Autowired
    private GetListMapper getListMapper;

    public User createTeacher(User user) throws AccessDenyException {
        return teacherDAO.createTeacher(user);
    }

    public List<User> getStudentList() throws NotFoundException {
            return studentDAO.getStudentList();
    }

    public List<User> getTeacherList() throws NotFoundException {
        return teacherDAO.getTeacherList();
    }

    public User searchStudent(String searchKey) throws NotFoundException {
        return studentDAO.getBySearchKey(searchKey);
    }

    public User searchTeacher(String searchKey) throws NotFoundException {
        return teacherDAO.getBySearchKey(searchKey);
    }

    public void modifyStudentInfo(User user) throws NotFoundException {
        studentDAO.getById(user.getId());
        studentDAO.update(user);
    }

    public void resetStudentPassword(User user) throws NotFoundException {
        studentDAO.getById(user.getId());
        studentDAO.resetPassword(user);

    }

    public void resetTeacherPassword(User user) throws NotFoundException {
        teacherDAO.getById(user.getId());
        teacherDAO.resetPassword(user);
    }

    public void modifyTeacherInfo(User user) throws NotFoundException {
        teacherDAO.getById(user.getId());
        teacherDAO.update(user);
    }

    public void deleteStudent(BigInteger studentId) throws NotFoundException {
        studentDAO.getById(studentId);
        studentDAO.delete(studentId);
    }

    public void deleteTeacher(BigInteger teacherId) throws NotFoundException {
        teacherDAO.getById(teacherId);
        teacherDAO.delete(teacherId);
    }

    public void deleteCourseByTeacherId(BigInteger teacherId){
        List<BigInteger>courseIdList = getListMapper.getCourseIdList(teacherId);
        for (BigInteger courseId:courseIdList){
            deleteKlassByCourseId(courseId);
            deleteMapper.deleteCourseMemberLimitStrategy(courseId);
            deleteMapper.deleteSeminarByCourseId(courseId);
            deleteRoundByCourseId(courseId);
            deleteMapper.deleteShareTeamApplicationByCourseId(courseId);
            deleteMapper.deleteTeamStrategyByCourseId(courseId);
        }
        deleteMapper.deleteCourseByTeacherId(teacherId);
    }

    public void deleteKlassByCourseId(BigInteger courseId){
        List<BigInteger>klassIdList = getListMapper.getKlassIdList(courseId);
        for (BigInteger klassId:klassIdList){
            deleteTeamByKlassId(klassId);
            deleteMapper.deleteKlassRoundByKlassId(klassId);
            deleteKlassSeminar(klassId);
            deleteMapper.deleteKlassStudentByKlassId(klassId);
            deleteMapper.deleteKlassTeamByKlassId(klassId);
        }
        deleteMapper.deleteKlassByCourseId(courseId);
    }

    public void deleteKlassSeminar(BigInteger klassId){
        List<BigInteger> klassSeminarIdList= getListMapper.getKlassSeminarIdList(klassId);
        for (BigInteger klassSeminarId :klassSeminarIdList){
            deleteMapper.deleteAttendanceByKSId(klassSeminarId);
            deleteMapper.deleteQuestionByKlassSeminarId(klassSeminarId);
            deleteMapper.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
        }
        deleteMapper.deleteKlassSeminarByKlassId(klassId);
    }

    public void deleteTeamByKlassId(BigInteger klassId){
        List<BigInteger> teamIdList = getListMapper.getTeamIdList(klassId);
        for(BigInteger teamId:teamIdList){
            deleteMapper.deleteTeamStudentByTeamId(teamId);
        }
        deleteMapper.deleteTeamByKlassId(klassId);
    }

    public void deleteRoundByCourseId(BigInteger courseId){
        List<BigInteger >roundIdList = getListMapper.getRoundIdList(courseId);
        for (BigInteger roundId : roundIdList){
            deleteMapper.deleteRoundScoreByRoundId(roundId);
        }
        deleteMapper.deleteRoundByCourseId(courseId);
    }

}
