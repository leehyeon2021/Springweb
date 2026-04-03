package springweb.board.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    // [ 업로드 경로 ]
    // 1. 로컬 환경
    private String baseDir = System.getProperty("user.dir"); // 주소(오타 주의)
    private String uploadDir = baseDir + "/build/resources/main/static/upload/"; // 상세 경로 추가. 업로드 위치를 build로 정함.
    // 2. 클라우드 환경 (나중에 함)


    // 1. 업로드
    public String upload( MultipartFile uploadFile ){
        // 1. `.isEmpty()`: 첨부파일 존재 여부 반환. 비어있니?->있다(false)/없다(true)
        System.out.println( uploadFile.isEmpty() ); // false
        // 2. `.getOriginalFilename()`: 첨부파일의 파일명
        System.out.println( uploadFile.getOriginalFilename() ); // 스크린샷 2026-03-23 204240.png
        // 3. `.getContentType()`: 첨부하일의 확장자 (유효성 검사시 사용)
        System.out.println( uploadFile.getContentType() ); // image/png
        // 4. `.getSize()`: 첨부파일의 용량. byte단위.
        System.out.println( uploadFile.getSize() ); // 140777

        // 1. 만약에 파일이 존재하지 않으면
        if( uploadFile == null || uploadFile.isEmpty() ){ return null; } // 업로드 실패: 파일 없음!
        // 2. 파일을 업로드할 경로 찾기: `File 파일객체 = new File( 파일경로 )`
            // 업로드할 uploadDir+파일을 file 객체 내 대입
            // 서버 경로 추가 - 개발자는 src에서만 -배포/실행-> 서버(build파일실행) <--- 클라이언트(사용자) build에게 요청 보냄.
        File uploadPath = new File( uploadDir );
        /// ***만약에 해당 경로의 폴더가 존재하지 않으면 폴더 생성
        if(uploadPath.exists() == false ){ // :file객체.exists() : 경로가 존재하면 true
            uploadPath.mkdir(); // file객체.mkdir(): 경로/폴더 생성
        }
        // 3. 업로드: 실제업로드경로 + 파일명
            // **만약에 서로 다른 사람(의 요청)이 동일한 파일명으로 다른 파일을 업로드하면 고유식별이 깨진다.**
            // 즉) 파일명은 동일하지만 다른 파일일 수 있다.
            // 고유식별: UUID vs 날짜/시간(밀리초) vs 게시물 번호(PK)
            // - 예시) 카카오톡 사진 받았을 때 파일명 날짜로 바뀜
        // UUID란? : 중복없는 난수 문자열 생성 함수 (고유성 보장)
            // ⭐ UUID_파일명 : 둘 사이를 언더바_로 구분하자. (나중에 분리해야하니까)
            // ⭐ UUID에는 언더바_ 절대 없다. -> 하지만 사용자가 올린 파일명에는 _언더바가 존재할 수 있다. => 파일명을 치환해줘야함. (언더바는 구분용으로 쓸 거니까)
        String uuid = UUID.randomUUID().toString();
        // 파일명과 경로를 연결해서 최종적인 경로 파일 객체를 생성.
        String fileName = uuid + "_" + uploadFile.getOriginalFilename().replaceAll("_","-");
            // 업로드할 파일명. uuid 더해주면 '이상한문자_원문.확장자' 이렇게 나옴.
        File uploadRealPath = new File( uploadDir + fileName );
        try {
            //  업로드 파일을 특정한 경로에 이송/복사한다. 예외처리 필수!
            uploadFile.transferTo( uploadRealPath );
            System.out.println("fileName: "+fileName);
            return fileName; // 업로드된 파일 이름은 DB에 저장하면 된대
        }catch(IOException e){ System.out.println( e ); }
        return null;
    }

}
