package com.julias.diary.diary.io.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class DiaryEntity implements Serializable {
    private static final long serialVersionUID = 7032980759075504261L;


    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable =false)
    private String diaryId;

    @Column(nullable =false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity userEntity;

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
