package com.julias.diary.diary.shared.dto;

import com.julias.diary.diary.io.entity.UserEntity;

import java.io.Serializable;

public class DiaryDto implements Serializable {

    private String userId;

    private static final long serialVersionUID = 1L;
    private long id;
    private String diaryId;
    private String title;

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
