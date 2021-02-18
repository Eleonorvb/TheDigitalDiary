package com.julias.diary.diary.service.impl;

import com.julias.diary.diary.io.entity.DiaryEntity;
import com.julias.diary.diary.io.entity.UserEntity;
import com.julias.diary.diary.repository.DiaryRepository;
import com.julias.diary.diary.repository.UserRepository;
import com.julias.diary.diary.service.DiaryService;
import com.julias.diary.diary.shared.dto.DiaryDto;
import com.julias.diary.diary.shared.dto.UserDto;
import com.julias.diary.diary.shared.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
Service class for handling CRUD for Diary object
 */

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public DiaryDto createDiary(DiaryDto diary) {
        DiaryEntity diaryEntity= new DiaryEntity();
        BeanUtils.copyProperties(diary, diaryEntity);

        String publicDiaryId=utils.generateUserId(30);
        diaryEntity.setDiaryId(publicDiaryId);
        diaryEntity.setTitle(diary.getTitle());
        diaryEntity.setUserEntity(userRepository.findByUserId(diary.getUserId()));

        DiaryEntity storedDiaryDetails = diaryRepository.save(diaryEntity);

        DiaryDto returnValue= new DiaryDto();
        BeanUtils.copyProperties(storedDiaryDetails, returnValue);

        return returnValue;
    }

    //Hämtar information om en diary
    @Override
    public DiaryDto getDiary(String diaryId) {
        DiaryEntity diaryEntity = diaryRepository.findByDiaryId(diaryId);

        if (diaryEntity == null) throw  new RuntimeException(diaryId);
        DiaryDto returnValue = new DiaryDto();
        BeanUtils.copyProperties(diaryEntity,returnValue);

        return returnValue;
    }

    //Hämtar alla diarys kopplade till en userId
    @Override
    public List<DiaryDto> getDiaryByUserId(String userId) {
        List<DiaryEntity> diaries=diaryRepository.findByUserEntity_userId(userId);
        List<DiaryDto> returnValue= new ArrayList<>();

        for (DiaryEntity diary: diaries) {
            DiaryDto diaryDto = new DiaryDto();
            BeanUtils.copyProperties(diary, diaryDto);
            diaryDto.setDiaryId(diary.getDiaryId());
            diaryDto.setTitle(diary.getTitle());
            returnValue.add(diaryDto);
    }
        return returnValue;
    }

}
