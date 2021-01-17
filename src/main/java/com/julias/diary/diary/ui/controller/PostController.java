package com.julias.diary.diary.ui.controller;


import com.julias.diary.diary.service.DiaryService;
import com.julias.diary.diary.service.PostService;
import com.julias.diary.diary.shared.dto.DiaryDto;
import com.julias.diary.diary.shared.dto.PostDto;
import com.julias.diary.diary.ui.model.request.DiaryRequestModel;
import com.julias.diary.diary.ui.model.request.PostRequest;
import com.julias.diary.diary.ui.model.response.DiaryRest;
import com.julias.diary.diary.ui.model.response.PostRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {


    @Autowired
    PostService postService;

    //GET is used to request data from a specified resource.
    @GetMapping(path = "/{id}")
    public PostRest getPost(@PathVariable String id){
        PostRest returnValue = new PostRest();

        PostDto postDto = postService.getPost(id);
        BeanUtils.copyProperties(postDto,returnValue);
        return returnValue;
    }



    //Metod för att hämta alla post som tillhör en diary

    @GetMapping(path = "/diary/{id}")
    public List<PostRest> getPostsByDiaryId(@PathVariable String id){
        List<PostRest> returnValue = new ArrayList<PostRest>();
        List<PostDto> postdto = postService.getPostsByDiaryId(id);

        for (PostDto postdtos: postdto){
            PostRest postRest=new PostRest();
            BeanUtils.copyProperties(postdtos,postRest);
            returnValue.add(postRest);
        }
        return returnValue;
    }



    @PostMapping
    public PostRest createPost(@RequestBody PostRequest postDetails){

        PostRest returnValue = new PostRest();
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postDetails, postDto);

        PostDto createdPost = postService.createPost(postDto);
        BeanUtils.copyProperties(createdPost, returnValue);

        return returnValue;
    }
}
