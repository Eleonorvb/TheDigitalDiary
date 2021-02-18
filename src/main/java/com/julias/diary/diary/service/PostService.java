package com.julias.diary.diary.service;

import com.julias.diary.diary.shared.dto.PostDto;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/*
Interface with methods for the PostService
 */

public interface PostService {
    PostDto createPost (PostDto post);
    PostDto upDatePost (PostDto post) throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException;
    PostDto getPost (String postId);
    String deletePostByPostId (String postId);
    List<PostDto> getPostsByDiaryId (String diaryId);
}
