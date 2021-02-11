package com.julias.diary.diary.service.impl;


import com.julias.diary.diary.io.entity.PostEntity;
import com.julias.diary.diary.repository.DiaryRepository;
import com.julias.diary.diary.repository.PostRepository;
import com.julias.diary.diary.service.PostService;
import com.julias.diary.diary.shared.dto.PostDto;
import com.julias.diary.diary.shared.utils.Crypt;
import com.julias.diary.diary.shared.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    Utils utils;

    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Override
    public PostDto createPost(PostDto post) {
        PostEntity postEntity= new PostEntity();
        BeanUtils.copyProperties(post, postEntity);

        String publicPostId=utils.generateUserId(30);
        postEntity.setPostId(publicPostId);
        postEntity.setPostTitle(post.getPostTitle());
        postEntity.setDiaryEntity(diaryRepository.findByDiaryId(post.getDiaryId()));

        PostEntity storedPostDetails = postRepository.save(postEntity);

        PostDto returnValue= new PostDto();
        BeanUtils.copyProperties(storedPostDetails, returnValue);

        return returnValue;
    }

    @Override
    public PostDto upDatePost(PostDto post)  {
        PostEntity postEntity = postRepository.findByPostId(post.getPostId());
        postMapper.updatePostFromDto(post, postEntity);
        PostEntity storedPostDetails = postRepository.save(postEntity);
        PostDto returnValue= new PostDto();
        BeanUtils.copyProperties(storedPostDetails, returnValue);
        return returnValue;
    }

    @Override
    public PostDto getPost(String postId) {
        PostEntity postEntity = postRepository.findByPostId(postId);

        if (postEntity == null) throw  new RuntimeException(postId);
        PostDto returnValue = new PostDto();
        BeanUtils.copyProperties(postEntity,returnValue);

        return returnValue;
    }

    @Override
    public List<PostDto> getPostsByDiaryId(String diaryId) {
        List<PostEntity> posts=postRepository.findByDiaryEntity_DiaryId(diaryId);
        List<PostDto> returnValue= new ArrayList<>();

        for (PostEntity post: posts) {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(post, postDto);
            postDto.setPostId(post.getPostId());
            postDto.setPostTitle(post.getPostTitle());
            postDto.setPostText(post.getPostText());
            postDto.setDiaryId(post.getDiaryEntity().getDiaryId());
            returnValue.add(postDto);
        }
        return returnValue;
    }

    @Override
    public String deletePostByPostId(String postId) {
        PostEntity postEntity = postRepository.findByPostId(postId);
        if (postEntity == null) throw  new RuntimeException(postId);

        postRepository.delete(postEntity);

        return "Post deleted";
    }
}
