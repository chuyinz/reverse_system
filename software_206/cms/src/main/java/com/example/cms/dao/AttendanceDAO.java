package com.example.cms.dao;

import com.example.cms.entity.Attendance;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class AttendanceDAO {
    @Autowired
    AttendanceMapper attendanceMapper;

    public BigInteger create(Attendance attendance) throws AccessDenyException {
        if (attendanceMapper.getAttendance(attendance.getKlassSeminarId(),attendance.getTeamId())!=null){
            throw new AccessDenyException("已报名");
        }
        attendanceMapper.create(attendance);
        attendance = attendanceMapper.getAttendance(attendance.getKlassSeminarId(),attendance.getTeamId());
        return attendance.getId();
    }

    public void setTeamOrder(Integer teamOrder, Attendance attendance){
        attendanceMapper.setTeamOrder(attendance.getId(),teamOrder);
    }

    public Attendance getAttendanceById(BigInteger id) throws NotFoundException {
        Attendance attendance;
        attendance = attendanceMapper.getAttendanceById(id);
        if (attendance==null){
            throw new NotFoundException("不存在该报名");
        }
        return attendance;
    }

    public Attendance getPresentAttendance(BigInteger kSId) throws NotFoundException {
        return attendanceMapper.getPresentAttendance(kSId);
    }

    public void delete(Attendance attendance){
        attendanceMapper.deleteById(attendance.getId());
    }

    public void deleteByKSId (BigInteger ksId){
        attendanceMapper.deleteByKSId(ksId);
    }

    public void updateReportById(BigInteger id, String url, String name){
        attendanceMapper.updateReportById(id, url, name);
    }

    public void updatePptById(BigInteger id, String url, String name){
        attendanceMapper.updatePptById(id, url, name);
    }

    public List<Attendance> getAttendanceList(BigInteger klassSeminarId){
        List<Attendance> attendanceList=attendanceMapper.getAttendanceList(klassSeminarId);
        return attendanceList;
    }



    public void update(Attendance attendance){
        attendanceMapper.update(attendance);
    }


    public Attendance getTeamAttendance(BigInteger kSId,BigInteger teamId) throws NotFoundException {
        Attendance attendance = attendanceMapper.getTeamAttendance(kSId, teamId);
        if (attendance==null){
            throw new NotFoundException("小组暂未报名");
        }
        return attendanceMapper.getTeamAttendance(kSId, teamId);
    }


}
