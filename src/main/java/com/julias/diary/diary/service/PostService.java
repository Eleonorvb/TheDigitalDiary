package com.julias.diary.diary.service;

import com.julias.diary.diary.shared.dto.DiaryDto;
import com.julias.diary.diary.shared.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost (PostDto post);
    PostDto getPost (String postId);
    List<PostDto> getPostsByDiaryId (String diaryId);
}
