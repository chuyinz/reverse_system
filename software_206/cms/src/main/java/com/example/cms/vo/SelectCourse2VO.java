package com.example.cms.vo;

import java.util.List;
/**
 * @author xzy
 * @date 2018-1-3
 */
public class SelectCourse2VO {


    public List<SelectCourseVO> getSelectCourseList() {
            return selectCourseList;
        }

        public void setSelectCourseList(List<SelectCourseVO> selectCourseList) {
            this.selectCourseList = selectCourseList;
        }

        public List<SelectCourseVO> getConflictCourseList() {
            return conflictCourseList;
        }

        public void setConflictCourseList(List<SelectCourseVO> conflictCourseList) {
            this.conflictCourseList = conflictCourseList;
        }



        public SelectCourse2VO(List<SelectCourseVO> selectCourseList, List<SelectCourseVO> conflictCourseList) {
            this.selectCourseList = selectCourseList;
            this.conflictCourseList = conflictCourseList;
        }
        public SelectCourse2VO()
        {

        }

        private List<SelectCourseVO>conflictCourseList;

        @Override
        public String toString() {
            return "SelectCourseVO2{" +
                    "conflictCourseList=" + conflictCourseList +
                    ", selectCourseList=" + selectCourseList +
                    '}';
        }

        private List<SelectCourseVO>selectCourseList;
    }


