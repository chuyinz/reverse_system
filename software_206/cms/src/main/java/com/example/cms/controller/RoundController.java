package com.example.cms.controller;

import com.example.cms.entity.*;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.CourseNotFoundException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.SeminarNotFoundException;
import com.example.cms.service.SeminarDisplayService;
import com.example.cms.service.SeminarManaService;
import com.example.cms.service.utils.FileUtils;
import com.example.cms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author xzy
 * @date 2018-12-25
 */
@RestController
@RequestMapping("/round")
public class RoundController {
    @Autowired
    private SeminarManaService seminarManaService;
    @PostMapping(value = "")
    public BigInteger createRound(@RequestAttribute("user") User user, @RequestBody Map<String,Object> map) throws ClassNotFoundException, AccessDenyException, CourseNotFoundException, SeminarNotFoundException {
        BigInteger courseId = new BigInteger(map.get("courseId").toString());
        Integer serial = new Integer(map.get("order").toString());
        Integer pScoreMethod = new Integer(map.get("pScoreMethod").toString());
        Integer rScoreMethod = new Integer(map.get("rScoreMethod").toString());
        Integer qScoreMethod = new Integer(map.get("qScoreMethod").toString());
        Round round = new Round(courseId,serial,pScoreMethod,rScoreMethod,qScoreMethod);
        return seminarManaService.createRound(round, user);
    }

    @GetMapping("/{roundId}")
    public RoundVO getRound(@PathVariable("roundId")BigInteger roundId, @RequestAttribute("user")User user) throws AccessDenyException, CourseNotFoundException, SeminarNotFoundException, NotFoundException, ClassNotFoundException {
        Round round = seminarManaService.getRound(roundId,user);
        List<KlassRound> klassRoundList = seminarManaService.getByRoundId(roundId);
        List<KlassRoundVO>klassRoundVOList = new ArrayList<KlassRoundVO>();
        for (KlassRound klassRound:klassRoundList){
            klassRoundVOList.add(new KlassRoundVO(klassRound.getKlassId(),seminarManaService.getKlassSerial(klassRound.getKlassId()),klassRound.getEnrollNumber()));
        }
        return new RoundVO(round, klassRoundVOList);
    }

    @RequestMapping(value = "/{roundId}",method = RequestMethod.PUT)
    public ResponseEntity modifyRound(@PathVariable("roundId")BigInteger roundId, @RequestBody ModifyRoundVO modifyRoundVO,@RequestAttribute("user")User user) throws AccessDenyException, CourseNotFoundException, SeminarNotFoundException, NotFoundException {
        Round round = new Round();
        round.setId(roundId);
        Integer i;
        switch (modifyRoundVO.getPresentationScoreMethod()){
            case "最高分":i=1;break;
            case "最低分":i=2;break;
            default:i=0;break;
        }
        round.setPresentationScoreMethod(i);
        switch (modifyRoundVO.getReportScoreMethod()){
            case "最高分":i=1;break;
            case "最低分":i=2;break;
            default:i=0;break;
        }
        round.setReportScoreMethod(i);
        switch (modifyRoundVO.getQuestionScoreMethod()){
            case "最高分":i=1;break;
            case "最低分":i=2;break;
            default:i=0;break;
        }
        round.setQuestionScoreMethod(i);
        List<KlassRound>klassRoundList = new ArrayList<KlassRound>();
        for (KlassRoundVO klassRoundVO:modifyRoundVO.getKlassRoundVOList()){
            klassRoundList.add(new KlassRound(klassRoundVO.getClassId(),roundId,klassRoundVO.getEnrollNumber()));
        }
        seminarManaService.modifyRound(round,klassRoundList ,user);
        return new ResponseEntity(HttpStatus.valueOf(204));
    }


    @GetMapping("/{roundId}/seminar")
    public List<SeminarVO> getAllSeminar(@PathVariable("roundId")BigInteger roundId) throws NotFoundException {
        List<Seminar> seminarList = seminarManaService.getAllSeminar(roundId);
        List<SeminarVO>seminarVOList = new ArrayList<SeminarVO>();
        for (Seminar seminar : seminarList){
            seminarVOList.add(new SeminarVO(seminar));
        }
        return seminarVOList;
    }


}
