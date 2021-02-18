package com.julias.diary.diary.ui.model.request;

/*
The client request model for the diary object
 */

public class DiaryRequestModel {
    private String diaryId;
    private String userId;
    private String title;

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
