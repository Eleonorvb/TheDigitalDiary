package com.julias.diary.diary.ui.controller;


import com.julias.diary.diary.service.PostService;
import com.julias.diary.diary.shared.dto.PostDto;
import com.julias.diary.diary.shared.utils.Crypt;
import com.julias.diary.diary.ui.model.request.PostRequestModel;
import com.julias.diary.diary.ui.model.response.PostRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/*
RestController for the post object with CRUD endpoints
 */

@RestController
@RequestMapping("posts")
public class PostController {


    @Autowired
    PostService postService;

    @Autowired
    Crypt crypt;

    //GET is used to request data from a specified resource.
    @GetMapping(path = "/{id}")
    public PostRest getPost(@PathVariable String id) throws InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
        PostRest returnValue = new PostRest();

        PostDto postDto = postService.getPost(id);
        BeanUtils.copyProperties(postDto,returnValue);

        returnValue.setPostText(crypt.decrypt(returnValue.getPostText()));
        returnValue.setPostTitle(crypt.decrypt(returnValue.getPostTitle()));

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public String deletePostByPostId(@PathVariable String id) {
        String deleteResponse = postService.deletePostByPostId(id);
        return deleteResponse;
    }

    //Metod för att hämta alla post som tillhör en diary

    @GetMapping(path = "/diary/{id}")
    public List<PostRest> getPostsByDiaryId(@PathVariable String id) throws InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
        List<PostRest> returnValue = new ArrayList<PostRest>();
        List<PostDto> postdto = postService.getPostsByDiaryId(id);

        for (PostDto postdtos: postdto){
            PostRest postRest=new PostRest();
            BeanUtils.copyProperties(postdtos,postRest);
            postRest.setPostTitle(crypt.decrypt(postRest.getPostTitle()));
            postRest.setPostText(crypt.decrypt(postRest.getPostText()));
            returnValue.add(postRest);
        }
        return returnValue;
    }



    @PostMapping
    public PostRest createPost(@RequestBody PostRequestModel postDetails) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        PostRest returnValue = new PostRest();
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postDetails, postDto);

        postDto.setPostText(crypt.encrypt(postDto.getPostText()));
        postDto.setPostTitle(crypt.encrypt(postDto.getPostTitle()));

        PostDto createdPost = postService.createPost(postDto);
        BeanUtils.copyProperties(createdPost, returnValue);

        returnValue.setPostTitle(crypt.decrypt(returnValue.getPostTitle()));
        returnValue.setPostText(crypt.decrypt(returnValue.getPostText()));

        return returnValue;
    }

    @PutMapping
    public PostRest updatePost(@RequestBody PostRequestModel postDetails) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        PostRest returnValue = new PostRest();
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postDetails, postDto);

        postDto.setPostText(crypt.encrypt(postDto.getPostText()));
        postDto.setPostTitle(crypt.encrypt(postDto.getPostTitle()));

        PostDto createdPost = postService.upDatePost(postDto);
        BeanUtils.copyProperties(createdPost, returnValue);

        returnValue.setPostTitle(crypt.decrypt(returnValue.getPostTitle()));
        returnValue.setPostText(crypt.decrypt(returnValue.getPostText()));

        return returnValue;
    }



}
