package com.julias.diary.diary.repository;

import com.julias.diary.diary.io.entity.DiaryEntity;
import com.julias.diary.diary.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DiaryRepository extends CrudRepository<DiaryEntity, Long> {
    List<DiaryEntity> findByUserEntity_userId(String userId);
    DiaryEntity findByDiaryId(String diaryId);

}
