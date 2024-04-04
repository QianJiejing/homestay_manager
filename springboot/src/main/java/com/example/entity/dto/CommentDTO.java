package com.example.entity.dto;

import com.example.entity.Comment;

public class CommentDTO {
    private Comment comment;
    private int allReplyCount;

    public CommentDTO (int allReplyCount){
        this.allReplyCount = allReplyCount;
    }

    public int getAllReplyCount() {
        return allReplyCount;
    }

    public void setAllReplyCount(int allReplyCount) {
        this.allReplyCount = allReplyCount;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
