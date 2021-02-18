package com.julias.diary.diary.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;

/*
Data transfer object for the Post object
 */

public class PostDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String diaryId;
    private String postText;
    private String postTitle;
    private String postId;
    private LocalDate localDate;


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

    public LocalDate getLocalDate() { return localDate; }

    public void setLocalDate(LocalDate localDate) { this.localDate = localDate; }
}
