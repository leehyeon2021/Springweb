/* 리액트 이벤트에서 주의할 점
1. onclick --> onClick. 합성이벤트(리액트가 핸들러/연결을 통해 이벤트를 실행하는 구조.)
2. onclick에서 함수를 _실행_하는 구조가 아니고 onClick으로 함수를 _**전달**_하는 구조!
    1. 함수의 선언: function 함수명(){}
    2. 함수의 호출: 함수명()
    3. 함수란? 여러 개 계산식/코드(데이터X) 묶음. 
    4. 변수란? 하나의 값을 저장하는 공간
    -> HTML: <button onclick="출력함수()"></button>
    -> REACT: <button onClick={출력함수}></button> . <button onClick={ () => {} }></button>
*/

function FrontComp( props ){ // onMyEvent1가 key이고 {()=>{alert('프론트엔드 클릭됨(부모전달)');}}가 값이다. props에 값이 전달됨.
    return(<>
        {/* JSX(return안쪽문법)에서 주석처리 하는 방법 - 중괄호! */}
        <li> {/* { event.preventDefault(); props.onMyEvent1(); } 이렇게 하면 a태그(에 이동위치 지정되지 않았을 때)의 기본인 깜빡임(새로고침) 사라짐*/}
            <a href="/" onClick={ ()=>{ props.onMyEvent1(); } } > 프론트엔드 </a>
        </li>
        <ul>
            <li>HTML5</li><li>CSS3</li><li>Javascript</li><li>jQuery</li>
        </ul>
    </>)
}

function Exam2( ){

    function 출력함수(){alert('선언적함수: 출력된문자1')}

    // 익명함수: 이름이 없는 함수. 재사용X, 일회성O or 이벤트함수O
    // 화살표함수: 이름이 없고 =>화살표 표현식을 사용하는 함수. (주로 변수에 저장하여 재사용한다.)
    return(<>
        <h2> 이벤트 처리 p.100 </h2>
        <button onClick={출력함수}>선언적함수</button>
        <button onClick={ function(){alert('익명함수: 출력된문자2');} }>익명함수</button>
        <button onClick={ ()=>{alert('화살표함수: 출력된문자3')} }>람다함수(화살표함수)</button>

        <ol> {/* onMyEvent1가 key이고 {()=>{alert('프론트엔드 클릭됨(부모전달)');}}가 값이다. */}
            <FrontComp onMyEvent1={ ()=>{alert('프론트엔드 클릭됨(부모전달)'); }}></FrontComp>
        </ol>
    </>)
}
export default Exam2