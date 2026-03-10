/* 컴포넌트 만드는 방법
1. 해당 폴더를 오른쪽 클릭 -> [new file]
2. 첫글자가 대문자로 시작하는 .jsx 파일확장자로 생성한다.
   - 예) Exam1.jsx
3. function 컴포넌트명(){}
4. 컴포넌트 내 return(<> jsx 문법 </>)
5. 파일 내 내보내기 할 컴포넌트 1개. `export default 컴포넌트명`

p.87 ~ p.88 (Exam1대신 App으로 되어있음) */
function FrontComp( props ){ // props는 함수/컴포넌트의 매개변수이며 프롭스 객체이다.
    const liRows = []; // 배열선언
    for( let i = 0; i<props.propData1.length;i++){
        liRows.push(
            <li key={i}> { props.propData1[i]} </li>
        );
    }
    return(<>
        <li>{ props.frTitle }</li>
            <ul>
                {liRows}
            </ul>
        
    </>)
}

const BackComp = ({ propData2 , baTitle })=>{
    const { a , b } = { a : 1 , b : 2 } // -> 구조분해: 객체 내 값들을 각 변수로 분해하는 것. 객체를 쪼개는 것.
        // 즉: 객체 내 값들을 각 변수로 분해. a속성 b속성으로 구성된 객체를 a변수 b변수로 분해한다.
        // 즉: props는 객체인데 const { 변수명1 , 변수명2 } = props
    
    const liRows=[];
    let keyCnt=0;
    for(let row of propData2){
        liRows.push(
            <li key={keyCnt++}>{row}</li>
        )
    }
    return(<>
        <li>{baTitle}</li>
        <ul>
            {liRows}
        </ul>
    </>)
}

// (1) 구조분해 없이 여러 개 속성을 한꺼번에 받기 (구조분해 살펴보기)
function MyComponent1( props ){
    // JSX(return말하는거)에선 주석이 안 된다. 
    return(<>
        <h2>props 객체 사용</h2>
        <p>
            {props.p1}, {props.p2}, {props.p3}, {props.p4}
        </p>
    </>)
}
// (2) 구조분해 사용하여 필요한 속성만 받기 (구조분해 살펴보기)
function MyComponent2( {p1 , p3 } ){ // 필요한 것만 꺼내기 {p1 , p3} <- 이렇게 하면 .안 찍고도 꺼낼 수 있음
    return(<>
        <p>
            {p1}, {p3}
        </p>
    </>)
}

function Exam1(){
    const frontData=['HTML5','CSS3','Javascript','jQuery','react'];
    const backData=['Java','Oracle','JSP','Spring Boot'];
    return(<>
        <h3> 프롭스 예제 p.87 </h3>
        <FrontComp propData1={frontData} frTitle="프론트엔드"></FrontComp>
        <BackComp propData2={backData} baTitle="백엔드"/>

        <h3> props 객체 p.90 </h3>
        <MyComponent1 p1={'HTML5'} p2={'CSS3'} p3={'Javascript'} p4={'jQuery'} />
        <MyComponent2 p1={'HTML5'} p2={'CSS3'} p3={'Javascript'} p4={'jQuery'} />
    </>)
}
export default Exam1;