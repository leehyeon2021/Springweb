package springweb.board.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springweb.board.dto.BoardDto;
import springweb.board.entity.BoardEntity;
import springweb.board.repository.BoardRepository;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service@Transactional @RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository; //서비스 간 호출/참조 가능.
    private final FileService fileService;


    // 1. 글쓰기 (회원)
    public boolean write(BoardDto boardDto, String loginMid ){
        // 1. Dto -> Entity
        BoardEntity saveEntity = boardDto.toEntity();
        // **** 저장하기 전에 FK 대입하기. FK의 엔티티를 찾아서 대입 ****
        // 현재 로그인 중인 mid로 엔티티 찾기
        Optional<MemberEntity> entityOptional = memberRepository.findByMid( loginMid );
        if(!entityOptional.isPresent()){
            System.out.println("없어요!"+loginMid);
            return false;} // 존재하지 않으면 존재하지 않은 회원이므로 실패.
        // ⭐ 저장할 게시물 엔티티에 `.set참조엔티티( 회원엔티티 );` 번호 넣는 거 아님.
        saveEntity.setMemberEntity( entityOptional.get() );

        // ***** 최종 DB에 엔티티를 SAVE하기 전에 첨부파일이 존재하면 업로드 *****
        String fileName = fileService.upload(boardDto.getUploadFile()); // dto내 multipartFile
        // 만약에 업로드 했다면 -> 저장할 엔티티에 업로드된 파일명 저장하기
        if( fileName != null ){ saveEntity.setBfile( fileName ); }

        // 2. entity 저장
        BoardEntity savedEntity = boardRepository.save(saveEntity);
        // 3. 만약
        if(savedEntity.getBno()>0){return true;}else{
            System.out.println("저장안됐어요!");return false;}
    }

    // 2. 전체조회
    public List<BoardDto> findAll(){
        return boardRepository.findAll(
                Sort.by(Sort.Direction.DESC, "bno"))  // .findAll( 페이징 , 정렬 )
                .stream() // 여러 개 자료를 갖는 자료(리스트/배열) 들의 순차적 처리 지원 함수
                //.filter() <- if문
                .map( BoardEntity :: toDto ) // 메소트 레퍼런스: 화살표 함수를 간결하게 사용하는 문법. 클래스명::함수명
                .toList(); // 리스트 타입으로 변환
    }

    // 3. 개별조회
    public BoardDto findOne( Long bno ){
        return boardRepository.findById( bno ) // .findById( PK번호 ): 개별 엔티티 조회
                .orElse( null ) // 만일 조회한 엔티티가 없으면
                .toDto(); // 엔티티가 존재하면 dto로 변환
    }

}
