// 컴포넌트 생성: export default function 컴포넌트명( props ){ return JSX문법 }

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
    return <>컴포넌트</>
}