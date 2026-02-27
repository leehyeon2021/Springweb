// 확인 중
console.log("index.js open");

// 요청 프로세스 : HTML -> JS(AXIOS) -> SPTING( CONTROLLER -> DAO ) -> MYSQL
// 응답 프로세스 : HTML <- JS(AXIOS) <- SPRING( CONTROLLER <- DAO ) <- MYSQL

// 1. 전체 조회
    // 실행조건: JS가 열렸을 때, 수정/등록/삭제 성공했을 때
    // 쓰던 거: function onfindAll(){}
    // 이제 쓸 거:
const onfindAll = async () => {
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
                        <td> <button onclick="onDelete()"> 삭제 </button>
                             <button onclick="onUpdate()"> 수정 </button> </td>
                    </tr>`;
        }

    // 3. 출력
    tbody.innerHTML = html;
}
onfindAll();

// 2. 등록
const onWrite = async () => {
    // 1. 가져오기
    const bcontent = document.querySelector("#bcontent").value;
    const bwriter = document.querySelector("#bwriter").value;
    // 2. 객체화
    const obj = { bcontent , bwriter };
    // 3. 동기화?
    const response = await axios.get("/board");
    const data = response.data;
    


}

// 3. 개별 수정

// 4. 개별 삭제