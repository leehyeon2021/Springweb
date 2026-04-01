import { useEffect, useState } from "react"

function MoveBox( props ){
    // props란? 상위 컴포넌트에서 해당 컴포넌트 호출 시 전달해주는 속성명과 속성값들(여러 개)을 객체로 받는 매개변수
    // props -> { initPosition: 50 } '50'이 들어옴

    // useState란? 컴포넌트 내 데이터(값) 상태 관리하는 함수, 왜? 재렌더링 하려고.
    // const[ 상태명 , set상태명 ] = useState( 초기값 );
    const [ position , setPosition] = useState( props.initPosition );
    const [ leftCount , setLeftCount ] = useState(1);
    // set을 쓰면 단순 수정이라도 현재 함수(MoveBox)가 재실행된다. == return이 다시 반환된다. 
        // setLifeCycle(3)한다면 LifeCycle의 return이 다시 반환된다.
        // 무한루프에 빠질 수 있기에 이벤트와 함께 사용한다. useState 무서운 친구!!

    // 0. css 문법을 객체 지향으로
    const boxStyle = {
        backgroundColor : 'red',
        position : 'relative',
        left : `${ position }px`, // CSS 요소의 JS 변수 대입. 즉-css를 변수화/객체화
        width : '100px', height : '100px',
        margin : '10px', 
        lineHeight : '100px'
    }

    // 1. 
    const moveLeft = () => {
        setPosition( ()=> position - 20 );
        setLeftCount( ()=> leftCount + 1 );
    }
    
    // 2. 
    const moveRight = () => {
        setPosition( ()=> position + 20 );
    }
    
    // 3. 
    useEffect( function(){
        // 최초 렌더링 시 실행. moveLeft 함수 실행.
        console.log("useEffect 실행 : 마운트");
        return()=>{
            // 재랜더링하면 기존 렌더링된 컴포넌트(화면/함수는) 지운다.
            console.log("useEffect 실행 : 언마운트");
        }
    } , [ leftCount ] );
    // 의존성 배열: state(상태)변수 배열 내 대입하여 해당하는 상태가 변경되면 useEffect 실행

    // 4.
    console.log("return 실행 : 렌더링");
    return(<>
        <div>
            <h4> 함수형 컴포넌트의 생명주기 </h4>
            <div style={boxStyle}> { leftCount } </div>
            <input type="button" value="좌측이동" onClick={ moveLeft }/>
            <input type="button" value="우측이동" onClick={ moveRight }/>
        </div>
    </>)
}


export default function Lifecycle( props ){

    // html에 없는 마크업들은 모두 컴포넌트 취급을 한다. 
    // 즉) 컴포넌트란? 나만의 마크업 만들기.

    return(<>
         <h2> chapter12 </h2>
         <MoveBox initPosition={ 50 }></MoveBox>
    </>)
}