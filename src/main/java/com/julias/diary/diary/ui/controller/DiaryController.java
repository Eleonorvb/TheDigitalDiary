package com.julias.diary.diary.ui.controller;


import com.julias.diary.diary.service.DiaryService;
import com.julias.diary.diary.shared.dto.DiaryDto;
import com.julias.diary.diary.shared.utils.Crypt;
import com.julias.diary.diary.ui.model.request.DiaryRequestModel;
import com.julias.diary.diary.ui.model.response.DiaryRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    Crypt crypt;


    //GET is used to request data from a specified resource.
    @GetMapping(path = "/{id}")
    public DiaryRest getTitle(@PathVariable String id) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        DiaryRest returnValue = new DiaryRest();

        DiaryDto diaryDto = diaryService.getDiary(id);
        BeanUtils.copyProperties(diaryDto,returnValue);
        System.out.println(returnValue.getTitle());
        returnValue.setTitle(crypt.decrypt(returnValue.getTitle()));

        return returnValue;
    }

    @GetMapping(path = "/user/{id}")
    public List<DiaryRest> getDiaryByUserId(@PathVariable String id) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        List<DiaryRest> returnValue = new ArrayList<DiaryRest>();
        List<DiaryDto> diaryDto = diaryService.getDiaryByUserId(id);

        for (DiaryDto diaryDtos: diaryDto){
            DiaryRest diaryRest=new DiaryRest();
            BeanUtils.copyProperties(diaryDtos,diaryRest);
            diaryRest.setTitle(crypt.decrypt(diaryRest.getTitle()));

            returnValue.add(diaryRest);
        }
        return returnValue;
    }

    // POST is used to send data to a server to create
    @PostMapping
    public DiaryRest createDiary(@RequestBody DiaryRequestModel diaryDetails) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        DiaryRest returnValue = new DiaryRest();
        DiaryDto diaryDto = new DiaryDto();
        BeanUtils.copyProperties(diaryDetails, diaryDto);

        diaryDto.setTitle(crypt.encrypt(diaryDto.getTitle()));

        DiaryDto createdDiary = diaryService.createDiary(diaryDto);
        BeanUtils.copyProperties(createdDiary, returnValue);

        returnValue.setTitle(crypt.decrypt(returnValue.getTitle()));

        return returnValue;
    }

}
