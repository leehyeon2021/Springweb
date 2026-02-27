console.log( "task.js exe" );

//1. 타입 : JS는 동적타입이므로 타입 명시 안 함.
   //1. 기본 자료
   console.log(3); console.log(true); console.log(3.14); console.log("안녕");
   //2. 배열
   console.log( [3, true, 3.14, "안녕"] );
   //3. 객체/JSON
   console.log( { "name" : "홍길동" , "age" : 60 } );
   //4. gkatn
   function func1(){}
//2. 다양한 함수 형식
function func2(){} // 선언적 함수 ( 바닐라 )
//function ( ) {} // 익명 함수
( ) => {} // 화살표(람다표현식) 함수 ( 리액트 표현 )
//3. 화살표 함수는 익명이므로 변수에 저장한다.
const func3 = ( ) => {}

/* =====================================================
AXIOS (에이시오스)
1. 정의
: 대표적으로 비동기/동기 통신 함수
   - AXIOS(REACT) vs. JS(FETCH) vs. JQUERY(AJAX) 등
2. 목적
: JS환경에서 서버와의 통신
3. 설치
   1. HTML에 CON 코드를 추가한다.
   2. <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
4. 사용법  */
// 1. `axios.HTTP메소드명( "통신할 주소" )` vs. 탈렌드API
axios.get("http://localhost:8080/day003/task") // post , put , delete 같은 거.
// 2. `axios.HTTP메소드명( "통신할 주소" ).then( ( response ) => { response.data }; )`
// response.data 하면 데이터를 확인 가능. response 하면 데이터 송수신 과정 다 확인 가능.
      // +) 프론트서버와 벡엔드서버가 같다면? -> 도메인 생략 가능!
axios.delete("/day003/task?name=홍길동").then( (response) => { console.log( response ); } )
axios.delete("/day003/task?name=홍길동").then( (response) => { console.log( response.data ); } )
// 3. axios.HTTP메소드명
axios.post("/day003/task?age=60")
     .then( (response) => { console.log( response.data ); } )
     .catch( (error) => { console.log( error ); } ); // JAVA의 try/catch 같은 애
// 4. `axios.HTTP메소드명( "통신할 주소" , body )` body에 넣을 것을 입력. body는 post랑 put만 가능하다.
const obj = { "name":"홍길동","age":40 } //객체 설정
axios.put("/day003/task" , obj )
     .then( ( r ) => { console.log( r.data ) } )
     .catch( ( e ) => { console.log( e ) } )

// - 비동기통신이란?: 요청 여러 개 했을 때 먼저 처리된 응답 부터 실행. ⤴️
//    - 즉: 먼저 처리된 로직부터 실행된다. 순서 보장 안 함.

// - 동기통신이란?: 여러 개 요청했을 때 먼저 요청한 로직이 응답하는 순서대로 실행 ⤵️
//    - 즉: 먼저 요청한 로직이 먼저 실행한다. 순서 보장 함.

// 5. `async` 동기화 키워드 (많이 쓴대)
const func5 = async ( ) => {
   try{ // 1. 예외처리한다.
        // 2. axios 앞에 await 키워드를 이용한 동기화
        // 3. axios 결과를 변수/상수에 대입 받는다.
      const response =
         await axios.get("/day003/task/axios?name=김자반") // 이 통신이 끝날 때까지 아무 것도 실행되지 않도록 대기 걸어둔 것. wait!
        // 4. 결과를 확인한다.
        console.log( response.data );
   }catch( e ){ console.log(e); }
}
func5(); // 함수 실행