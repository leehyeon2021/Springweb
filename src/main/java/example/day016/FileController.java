package example.day016;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {
    private final FileService fileService;

    // 1. 서버에 파일 업로드
    // 탈란드 넣을 때
        // method: post
        // url:  http://localhost:8080/api/file
        // Headers: Content-Type , multipart/form-data
        // Body: uploadFile, File
    @PostMapping("")
    public ResponseEntity<?> upload(MultipartFile uploadFile ){ // @MultipartFile 스프링에 있는 애. @Request애들 쓰면 안 된다.
        System.out.println("FileController.upload");        // FileController.upload
        System.out.println("uploadFile = " + uploadFile);   // uploadFile = org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@bf5a0d7
        return ResponseEntity.ok(fileService.upload(uploadFile));
    }

    // 2. 서버에 파일 다운로드
    // 3. 서버에 파일 삭제

}

/*
1. 업로드: 클라이언트가 서버에게 데이터파일)을 전송하는 행위
2. 다운로드: 서버가 클라이언트에게 데이터(파일)을 전송하는 행위
3. 스트림: 데이터가 이동하는 흐름. 스트림API/파일처리/네트워크 등등
4. 버퍼: 데이터가 이동하는 흐름 감의 처리 속도를 일정하게 하기 위한 임시 메모리
    **흐름하는 순간에도 누군가는 기억을 해야 한다. -> 버퍼가 기억한다.**
5. 자바입출력 클래스
   1. `FileInputStream`
   2. `ServletOutputStream`

[ 스프링 파일 업로드 구현 ] -> 내장 업로드 객체를 지원한다.
1. `MultipartFile` 클래스 : 첨부파일(바이트) 매핑하는 인터페이스
2. 사용법
   - 첨부파일 1개: MultipartFile 변수명
   - 첨부파일 N개: List<MultipartFile> 변수명
3. Content-Type: multipart/form-data

 */

/*
Content-Type을 까먹은 바보를 위해...
1. 쿼리스트링 방식: URL?변수명=값
2. 본문 방식: URL(x). {"변수명" : 값} , content-type: application/json
3. 대용량(byte) 방식: URL(x). form형식. content-type: multipart/form-data
 */