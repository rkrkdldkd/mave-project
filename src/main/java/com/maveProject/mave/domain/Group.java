package com.maveProject.mave.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    private int flowerCount;

    private int remainAnswerCount;

    private LocalDateTime questionTime;

    private Long diaryDate;

    private Long completeDate;

    @Enumerated(EnumType.STRING)
    private IsFinish status;

    @Enumerated(EnumType.STRING)
    private Flower flower;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();


    //===== 생성 메서드 =====//

    public Group(String groupName, LocalDateTime questionTime, IsFinish status, Flower flower, Long diaryDate, Long completeDate) {
        this.groupName = groupName;
        this.questionTime = questionTime;
        this.status = status;
        this.flower = flower;
        this.diaryDate = diaryDate;
        this.completeDate = completeDate;

    }


    //===== 연관관계 메서드 =====//

    //====== 비즈니스 메서드 =====//

    public Boolean isDateChanged(Long prevDate, Long nextDate){
        if(prevDate < nextDate){
            return true;
        }
        return false;
    }

    public void setCompleteDate(Long completeDate){
        this.completeDate = completeDate;
    }

    public Long changeDiaryDate(){
        LocalDate startDate = LocalDate.of(
                this.questionTime.getYear(),
                this.questionTime.getMonth(),
                this.questionTime.getDayOfMonth());
        Long between =  ChronoUnit.DAYS.between(startDate, LocalDate.now());
        this.diaryDate = between + 1;
        return diaryDate;
    }

    public void minusCount() {
        if (this.getRemainAnswerCount() == 0) {
            return;
        }
        this.remainAnswerCount--;
    }

    public int setCount() {
        remainAnswerCount = this.getMembers().size();
        return remainAnswerCount;
    }

    public void changeIsFinish() {
        status = IsFinish.NO;
    }

    public Boolean compareState() {
        if (remainAnswerCount == 0) {
            status = IsFinish.YES;
            plusFlowerCount();
            return true;
        }
        return false;
    }

    public void plusFlowerCount() {
        flowerCount++;
    }

    public void flowerStatusCheck() {
        switch (flowerCount) {
            case 1:
                flower = flower.ONE;
                break;
            case 2:
                flower = flower.TWO;
                break;
            case 3:
                flower = flower.THREE;
                break;
            case 4:
                flower = flower.FOUR;
                break;
            case 5:
                flower = flower.FIVE;
                break;
            default:
                flower = flower.ZERO;
                break;
        }

        if(flowerCount > 5){
            flower = flower.FIVE;
        }
    }


}
