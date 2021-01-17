package com.julias.diary.diary.repository;

import com.julias.diary.diary.io.entity.DiaryEntity;
import com.julias.diary.diary.io.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findByDiaryEntity_DiaryId(String diaryId);
    PostEntity findByPostId(String postId);
}
