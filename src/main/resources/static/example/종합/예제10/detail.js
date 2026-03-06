console.log('datil.js');

// 개별 조회 함수 정의
// 1. 쿼리스트링이란? URL(주소) 뒤에 ?로 매개변수(값) 포함하는 경우
// 2. JS에서 쿼리스트링의 값 가져오는 방법
//   - new URLSearchParams( location.search ).get( 변수명 );
const bno = new URLSearchParams( location.search ).get( "bno" );
// 3. 확인
console.log( 'bno: '+bno );

// ===============================================

// 개별조회 함수 정의
const 개별조회 = async () => {
    // 1. 어디에
    const boardBox = document.querySelector("#boardBox");
    // 2. 무엇을. 쿼리스트링에 존재하는 클릭된 게시물 bno에 대한 정보.
    const response = await axios.get(`/board/detail?bno=${bno}`); // ?쿼리스트링 방법
    const data = response.data;
        // +=은 누적. =은 대입
    let html = ` <br/>제목<div>  :  ${data.btitle} </div><br/>
                       작성자<div> :  ${data.bwriter} </div><br/>
                       작성일<div> :  ${data.createDate} </div><br/>
                       내용 <div> :  ${data.bcontent}</div><br/>
                       <button onclick="개별수정(${data.bno})"> 수정 </button>
                       <button onclick="개별삭제(${data.bno})"> 삭제 </button><br/>`;
    // 3. 출력
    boardBox.innerHTML = html;
}
개별조회();

// 개별 삭제 함수 정의
const 개별삭제 = async ( bno ) => {
    // 1. 현재 게시물 삭제하기 위해 션재게시물번호 확인 (bno는 매개변수 또는 쿼리스트링으로 가져오기.)
    // 2. axios 이용하여 서버에게 게시물 삭제 요청 결과받기
    const response = await axios.delete(`/board?bno=${bno}`);
    const data = response.data;
    // 3. 결과
    if(data == true ){
        alert('삭제 성공'); location.href="/example/종합/예제10/index.html";
    }else{alert('삭제 실패')}
}

// 개별 수정 함수 정의 - 원래 수정 페이지가 따로 있어야 한대
const 개별수정 = async( bno ) => {
    // 1. 현재 게시물을 수정하기 위해 현재 게시물 번호 확인하기
    console.log( '수정 번호: '+bno );
    // 2. 수정할 내용 입력받기. 테스트용. 원래는 prompt로 수정 받지 않고 페이지로 만든다.
    const btitle = prompt('수정할 제목을 입력하시오.');
    const bcontent = prompt('수정할 내용를 입력하시오.');
    const bwriter = prompt('수정할 작성자를 입력하시오.');
    // 3. axios 이용한 서버에게 수정 요청 응답 받기
    let obj = { bno, btitle , bcontent , bwriter}
    const response = await axios.put(`/board`,obj);
    const data = response.data;
    // 4. 결과
    if(data==true){alert('수정 성공'); location.reload();} // location.reload() : 새로고침?
    else{alert('수정 실패')}
}

// =========================================

const con = new URLSearchParams( location.search ).get( "con" );
console.log( 'con: '+con );

// 댓글전체조회
const 댓글조회 = async (bno) =>{
    // 1. 어디에
    const body = document.querySelector("#comment");
    // 2. 무엇을
    const response = await axios.get(`/comment`);
    const data = response.data;
    let html =``;
    // 3. 넣어
    for(let i=0;i<=data.length-1;i++){
        const list = data[i];
        if(list.bno===bno){
            html += `<div style="border: solid 1px black; padding: 10px;">
                        <span style="font-size: 20px;font-weight: bold;">${list.cwriter}</span><br/>
                        <span style="font-size: 10px;"> 작성일: ${list.createDate} | 수정일: ${list.updateDate} </span><br/>
                        <div style="padding: 5px;">${list.ccontent}</div>
                        <button onclick="댓글수정()" style="font-size: 10px;">댓글수정</button><button onclick="댓글삭제()" style="font-size: 10px;">댓글삭제</button>
                    </div><br/>`;
        }
    }
    body.innerHTML=html;
}
댓글조회();

// 댓글쓰기
const 댓글쓰기 = async (cno) =>{
    // 1. dom
    const writerInput=document.querySelector("#cwriter");
    const contentInput=document.querySelector("#ccontent");
    // 2. 값
    const cwriter=writerInput.value;
    const ccontent=contentInput.value;
    // 3. 객체화
    let obj={cno,criter,ccontent};
    // 4. 요청/응답
    const response = await axios.post();

}

// 댓글삭제
const 댓글삭제 = async () =>{

}

// 댓글수정
const 댓글수정 = async () =>{

}