package com.julias.diary.diary.service;

import com.julias.diary.diary.shared.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost (PostDto post);
    PostDto upDatePost (PostDto post);
    PostDto getPost (String postId);
    String deletePostByPostId (String postId);
    List<PostDto> getPostsByDiaryId (String diaryId);
}
