package com.example.cms.dao;

import com.example.cms.entity.Question;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class QuestionDAO {
    @Autowired
    QuestionMapper questionMapper;

    public BigInteger create(Question question){
        questionMapper.create(question);
        return questionMapper.getId(question);
    }

    public List<Question> getQuestionList(BigInteger kSId) throws NotFoundException {
        List <Question>questionList=questionMapper.getQuestionList(kSId);
        if (questionList.size()==0){
            throw new NotFoundException("问题未找到");
        }
        return questionList;
    }

    public void updateScore(BigInteger questionId, BigDecimal score){
        questionMapper.updateScore(questionId, score);
    }

    public Question getQuestion(BigInteger id) throws NotFoundException {
        Question question = questionMapper.getQuestion(id);
        if (question==null){
            throw new NotFoundException("问题未找到");
        }
        return  question;
    }


    public List<Question> getTeamQuestionList(Question question) {
        List <Question>questionList=questionMapper.getTeamQuestionList(question);
        return questionList;
    }

}
