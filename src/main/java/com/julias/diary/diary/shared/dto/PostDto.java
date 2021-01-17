package com.julias.diary.diary.shared.dto;

import java.io.Serializable;

public class PostDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String diaryId;
    private String postText;
    private String postTitle;
    private String postId;


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
}
