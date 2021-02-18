package com.julias.diary.diary.service;

import com.julias.diary.diary.shared.dto.DiaryDto;
import com.julias.diary.diary.shared.dto.UserDto;

import java.util.List;

/*
Interface with methods for the DiaryService
 */

public interface DiaryService {
    DiaryDto createDiary (DiaryDto diary);
    DiaryDto getDiary (String diaryId);
    List<DiaryDto> getDiaryByUserId (String userId);
}
