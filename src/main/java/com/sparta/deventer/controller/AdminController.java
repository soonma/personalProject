package com.sparta.deventer.controller;

import com.sparta.deventer.dto.*;
import com.sparta.deventer.security.UserDetailsImpl;
import com.sparta.deventer.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 공지글 생성
    @PostMapping("/posts/notice")
    public ResponseEntity<PostResponseDto> createNoticePost(
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        PostResponseDto postResponseDto = adminService.createNoticePost(postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(postResponseDto);
    }

    // 공지글 수정
    @PutMapping("/posts/notice/{postId}")
    public ResponseEntity<PostResponseDto> updateNoticePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostRequestDto updatePostRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        PostResponseDto postResponseDto = adminService.updateNoticePost(postId, updatePostRequestDto, userDetails.getUser());
        return ResponseEntity.ok(postResponseDto);
    }

    // 어드민 권한으로 게시물 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deleteAnyPost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        adminService.deleteUserPost(postId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }

}