// 확인 중
console.log("index.js open");

// 요청 프로세스 : HTML -> JS(AXIOS) -> SPTING( CONTROLLER -> DAO ) -> MYSQL
// 응답 프로세스 : HTML <- JS(AXIOS) <- SPRING( CONTROLLER <- DAO ) <- MYSQL

// 1. 전체 조회
    // 실행조건: JS가 열렸을 때, 수정/등록/삭제 성공했을 때
    // 쓰던 거: function onfindAll(){}
    // 이제 쓸 거:
const onFindAll = async () => {
    // 1. 어디에 document.querySelector( 출력할 위치 )
    const tbody = document.querySelector("#boardTable tbody");

    // 2. 무엇을 (출력할 내용물 정의) (( AXIOS 사용 ))
    let html = "";

        // 동기화 (axios: JS가 스프링과 통신하는 기술.)
        //    1. 현재 함수명 앞에 async(동기화) 키워드를 붙인다.
        //    2. axios 앞에 await 키워드를 붙인다. 
        const response = await axios.get("/board"); //console.log( response );
        const data = response.data; // response: 응답정보객체, response.data: 응답값(return)

        for( let index = 0 ; index <= data.length-1; index++){
            const board = data[index];
            html+= `<tr>
                        <td>${board.bno}</td> <td>${board.bcontent}</td> <td>${board.bwriter}</td> <td>${board.bdate}</td>
                        <td> <button onclick="onDelete(${ board.bno })"> 삭제 </button>
                             <button onclick="onUpdate(${ board.bno })"> 수정 </button> </td>
                    </tr>`;
        }
    // 3. 출력
    tbody.innerHTML = html;
}
onFindAll();

// 2. 등록
    // 실행조건: 등록버튼 클릭했을 때
const onWrite = async () => {
    // 1, 2. 입력 받은 DOM 객체 가져오기, 가져온 DOM 객체 내 입력받은 값 꺼내기(value)
    const bcontentInput = document.querySelector("#bcontent");
    const bwriterInput = document.querySelector("#bwriter");
    const bcontent = bcontentInput.value;
    const bwriter = bwriterInput.value;
    // 3. 객체화
    const obj = { bcontent , bwriter }; //const obj = { "bcontent" : bcontent , "bwriter" : bwriter };
    // 4. (1개월차)배열 저장 --> (3개월차) AXIOS 이용하여 서버에게 저장을 요청한다.
        //    1. 현재 함수명 앞에 async(동기화) 키워드를 붙인다.
        //    2. axios 앞에 await 키워드를 붙인다. 
    const response = await axios.post("/board" , obj ); // axios.HTTP메소드명( "통신할주소", body본문 );
    const data = response.data;
    if(data==true){
        alert("등록 성공"); bcontentInput.value=''; bwriter.value=''; // value값 비워주기
        onFindAll(); // 새로고침
    }else{alert("등록 실패");} 
}

// 3. 개별 수정
const onUpdate = async ( bno ) => {
    // 1. 새로운 수정 내용 입력받기
    const bcontent = prompt("수정할 내용");
    // 2. 객체(Body) 구성 , 속성명과 대입할 변수명이 **같다면** 속성명 생략
    const obj = { bno , bcontent };
    // 3. axios 이용하여 서버에게 수정 요청 후 응답 받기
    const response = await axios.put(`/board` , obj);
    const data = response.data;
    // 4. 결과 제어
    if(data == true){
        alert("수정 완료");
        onFindAll; // 새로고침
    }else{alert("수정 실패");}    
}

// 4. 개별 삭제
    // 1. 삭제할 번호를 매개변수로 받는다.
const onDelete = async ( bno ) => {
    // 2. axios 활용하여 삭제할 번호를 서버에게 쿼리스트링으로 요청하고 결과를 응답받는다.
    const response = await axios.delete(`/board?bno=${ bno }`);
    const data = response.data;
    // 3. 결과에 따른 코드 제어
    if( data == true ){
        alert("삭제 성공");
        onFindAll(); // 새로고침
    }else{alert("삭제 실패")}
}