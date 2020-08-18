package com.example.cms.vo;

import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class AttendanceListVO {
    KlassVO klass;
    List<AttendanceVO>attendanceList;
    Integer isEnroll;

    @Override
    public String toString() {
        return "AttendanceListVO{" +
                "klass=" + klass +
                ", attendanceList=" + attendanceList +
                ", isEnroll=" + isEnroll +
                '}';
    }

    public KlassVO getKlass() {
        return klass;
    }

    public void setKlass(KlassVO klass) {
        this.klass = klass;
    }

    public List<AttendanceVO> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<AttendanceVO> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public Integer getIsEnroll() {
        return isEnroll;
    }

    public void setIsEnroll(Integer isEnroll) {
        this.isEnroll = isEnroll;
    }

    public AttendanceListVO(KlassVO klass, List<AttendanceVO> attendanceVOList,Integer isEnroll) {
        this.klass = klass;
        this.attendanceList = attendanceVOList;
        this.isEnroll = isEnroll;
    }
}
