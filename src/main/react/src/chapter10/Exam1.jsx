
// 얕은 비교 실습
    // 새로고침 수동으로 하면: 초기화 됨. 영구저장이 안 되어서 백엔드를 해야 하는 것.

// 컴포넌트 생성: export default function 컴포넌트명( props ){ return JSX문법 }

import { useState } from "react"

// 컴포넌트2
const TopComp = ( { MyData } ) =>{

/*((복습))
- const 상수, let 변수
- () => {} 화살표함수 = function(){} 익명함수 = function 함수명(){} 선언적함수
- { MyData } 구조분해: 상위컴포넌트로 부터 props 객체를 구조화해서 변수로 받기. props 객체 내 MyData속성값을 변수로 받는다.
- 리액트JSX return: JSX 반환. (JSX란: HTML + JS 조합된 문법) */
// - JSX 주석: {/* 이것은 주석입니다 */}
// - JAVA MAP , 배열/리스트.straam.map( (반복변수) -> { } )
// - JS MAP , 배열/리스트.map( (반복변수) => { } )
// - Map vs. forEach : 리턴 있음/없음 차이. JSX에서는 리턴 있는 Map을 자주 사용. HTML 리턴해야 함.
    // 면접 때 물어보는 것 중 하나. instance와 객체의 차이 같은 것도.
    // JSX 문법에서 반복된 자료를 HTML로 구성하여 반환하는 구조 다수. MAP 활용 된다.
    /* MyData.front = ['A', 'B', 'C'] 
    3+3=6 , <li key={ i } > {A} </li> <li key={i}> {B} </li> <li key={i}> {C} </li> */
    
    return (<>
        <ol> 
            <li>프론트엔드</li>
            <ul>
                {MyData.front.map((item, index) => <li key={ index } > {item} </li>)}
            </ul>
            <li> 백엔드 </li>
            <ul>
                {MyData.back.map((item, index) => <li key={ index }> {item} </li>)}
            </ul>
        </ol>
    </>)

}

// 컴포넌트1
export default function Exam1( props ){

    const [ MyData , setMyData ] = useState( {  // 객체를 초깃값으로 상태 정의
        front: ['HTML', 'CSS', 'Javascipt', 'jQuery'],
        back: ['Java', 'Oracle', 'JSP', 'Spring Boot']
    } )
    // useState 훅(hook: 갈고리-연결. 상태/값/데이터<--갈고리-->컴포넌트)
        // 즉) 상태(state)가 (*주소/참조)값 변경되면 컴포넌트 렌더링 된다.
        // const [상태명 , set상태명] = useState( 초기값 );
    // 리터럴: 키보드로 부터 입력받은 상수 자료. 주소/참조값: 자료가 위치한 메모리 주소값
        // let a = 3; -> 주소값은 2개. 3(101번지) , a(201번지)
            //+) let b = 3; -> 주소값은 3개. 3(101번지) , a(201번지) , b(202번지)
        // ExamDto dto1 = new ExamDto(); -> 주소값은 2개. new ExamDto(301번지) , dto1(401번지)
            //+) ExamDto dto2 = new ExamDto(); -> 주소값은 4개. new ExamDto(302번지) , dto2(402번지)
            //+) ExamDto dto3 = dto2; -> 주소값은 5개. dto3(403번지)
    
    // 1. 렌더링 안 되는 코드 : 동일한 주소값의 값을 추가했으므로 새로고침 안 된다.
    const addFront = () => { // 리렌더링x
        MyData.front.push('React'); // MyData(101동203호). push(): 배열 내 값 추가하는 함수. 주소 변경 없이 주소 내 값 추가.
        setMyData(MyData); // set상태명(새로운주소값); 상태는 값 변경 감지하지 않음. 주소값 변경만 감지. (얕은 비교)
    }

    // 2. 렌더링 되는 코드 ( 스프레드연산자 사용: {...기존객체} , [...기존배열] , {...기존객체, 속성명:새로운값} , [...기존배열, 새로운값] )
    const addBack = () => {  // 복사본 만든 후 추가, 리렌더링o 새로고침 된다.
        const newBack = [ ...MyData.back , 'Node.js'];
        const newMyData =  {...MyData , back: newBack }; // newMyData = {...MyData(101동203호)}
        setMyData(newMyData); // document.innerHtml 대신.   // newMyData 105동 702호
        
    }

    return (<>
        <h2>Chapter 10 </h2>
        <TopComp MyData={MyData}/>
        <button type="button" onClick={addFront}>프론트 추가</button>
        <button type="button" onClick={addBack}>백엔드 추가</button>
    </>)
}

/*
onclik: HTML코드. onClick: JSX코드(가상/가짜DOM)
리액트가 가상/가짜 DON 사용하는 이유: HTML이 객체지향이 아니라서 HTML를 객체지향으로 만든 구조 JSX
DOM: document object model: html들을 마크업들을 객체지향 */