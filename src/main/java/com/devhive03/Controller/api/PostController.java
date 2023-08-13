package com.devhive03.Controller.api;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.devhive03.Controller.ExceptionControll.FileIsNotIOException;
import com.devhive03.Controller.ExceptionControll.ResourceNotFoundException;
import com.devhive03.Model.DAO.Lecture;
import com.devhive03.Model.DAO.Post;
import com.devhive03.Model.DAO.PostPicture;
import com.devhive03.Model.DAO.User;
import com.devhive03.Model.DTO.Post.*;
import com.devhive03.Model.DTO.User.UserWriterDTO;
import com.devhive03.Repository.*;
import com.devhive03.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "게시글", description = "게시글 API")
@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor
public class PostController {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final PostService postService;
    private final PostDAORepository postDAORepository;
    private final PostPictureDAORepository postPictureDAORepository;
    private final UserDAORepository userDAORepository;
    private final LectureDAORepository lectureDAORepository;
    private final AmazonS3 amazonS3Client;
    private final PostLikesListDAORepository postLikesListDAORepository;
    private final FavoritesDAORepository favoritesDAORepository;

    //설명추가
    @Operation(summary = "게시글 조회", description = "특정 게시글 조회")
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    @ApiResponse(responseCode = "400", description = "게시글 조회 실패", content = @Content(examples = @ExampleObject(value = "{\n" +
            "  \"message\": \"Post not found with id 1\",\n" +
            "}")))
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Long postId, @RequestParam(required = false) Long userId) {
        //fetch join으로 모든 정보 한번에 조회
        Optional<Post> findPost = postDAORepository.findPostId(postId);
        if (findPost.isEmpty()) {
            throw new ResourceNotFoundException("Post not found with id " + postId);
        }

        Post post = findPost.get();
        //PostDTO로 변환
        PostDTO postDTO = PostDTO.of(post);

        //userId가 null이 아니면 좋아요, 찜 null
        if (userId != null) {
            //좋아요 여부
            boolean isLike = post.getPostLikesLists().stream().anyMatch(postLikesList -> postLikesList.getUser().getId().equals(userId));
            postDTO.setIsLike(isLike);

            //찜 여부
            boolean isFavorite = post.getFavorites().stream().anyMatch(favorites -> favorites.getUser().getId().equals(userId));
            postDTO.setIsFavorite(isFavorite);
        }

        return ResponseEntity.ok(postDTO);
    }

    // Get Posts by WriterId
    @Operation(summary = "특정 유저가 작성한 게시글 조회", description = "특정 유저가 작성한 게시글 조회")
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    @ApiResponse(responseCode = "400", description = "게시글 조회 실패", content = @Content(examples = @ExampleObject(value = "{\n" +
            "  \"message\": \"User not found with id 1\",\n" +
            "}")))
    @GetMapping(value = "/writer/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostsDTO> getAllPostBy(@PathVariable("userId") Long userID) {
        List<Post> posts = postDAORepository.findAllByWriterId(userID);

        Optional<User> findWriter = userDAORepository.findById(userID);

        if(findWriter.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id " + userID);
        }

        UserWriterDTO userWriterDTO = UserWriterDTO.of(findWriter.get());

        List<PostItemDTO> postItemDTOS = posts.stream().map(PostItemDTO::of).collect(Collectors.toList());

        PostsDTO postsDTO = new PostsDTO(userWriterDTO, postItemDTOS);
        return ResponseEntity.ok(postsDTO);
    }

    // Get All Posts
    @Operation(summary = "검색 내용이 포함되는 게시글 조회", description = "검색 내용이 복수로 들어갈수 있으며 OR로 검색됩니다.")
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    @GetMapping
    public ResponseEntity<List<PostItemDTO>> getAllPosts(PostParamsDTO postParamsDTO) {
        postParamsDTO.nullCheck();
        List<Post> posts = postDAORepository.findAllByPostParamsDTO(postParamsDTO.getPostTitle(), postParamsDTO.getLectureName(), postParamsDTO.getMajor(), postParamsDTO.getProfessor());

        List<PostItemDTO> postItemDTOS = posts.stream().map(PostItemDTO::of).collect(Collectors.toList());

        return ResponseEntity.ok(postItemDTOS);
    }
    @Operation(summary = "게시글 생성", description = "이미지와 게시글 정보를 받아 게시글을 생성합니다.(swagger작동안됌)")
    @ApiResponse(responseCode = "200", description = "게시글 생성 성공")
    @ApiResponse(responseCode = "400", description = "게시글 생성 실패", content = @Content(examples = @ExampleObject(value = "{\n" +
            "  \"message\": \"User not found with id 1\",\n" +
            "}")))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDTO> createPost(
            @RequestPart(value = "pictures") List<MultipartFile> pictures,
            @RequestPart(value = "data") PostFormDTO postFormDTO
    ) {
        Optional<User> user = userDAORepository.findById(postFormDTO.getUserId());
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id " + postFormDTO.getUserId());
        }
        Optional<Lecture> lecture = lectureDAORepository.findById(postFormDTO.getLectureId());
        if(lecture.isEmpty()) {
            throw new ResourceNotFoundException("Lecture not found with id " + postFormDTO.getLectureId());
        }

        Post post = new Post();
        post.setWriter(user.get());
        post.setLecture(lecture.get());
        post.setPostTitle(postFormDTO.getPostTitle());
        post.setPostContent(postFormDTO.getPostContent());
        post.setPrice(postFormDTO.getPrice());


        Post savedPost = postDAORepository.save(post);

        try {
            for (MultipartFile picture : pictures) {
                String fileName = savedPost.getPostId() + "/" + picture.getOriginalFilename();
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(picture.getContentType());
                metadata.setContentLength(picture.getSize());
                //Upload to S3 with Public Read Permission
                amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, picture.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead));
                String fileUrl = amazonS3Client.getUrl(bucket, fileName).toString();
                PostPicture postPicture = new PostPicture();
                postPicture.setPost(savedPost);
                postPicture.setPicture(fileUrl);
            }
        } catch (IOException e) {
            throw new FileIsNotIOException("파일입출력에 실패했습니다.");
        }

        postPictureDAORepository.saveAll(savedPost.getPostPictures());
        PostDTO postDTO = PostDTO.of(post);
        return ResponseEntity.ok(postDTO);
    }
}
