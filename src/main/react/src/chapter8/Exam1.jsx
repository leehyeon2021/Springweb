/* 
export default function Exam1( props ){
    return(<></>)
}
*/

import bird from "/src/assets/bird2.png" // 이미지 불러오는 방법2
import "./index.css" // <-- 현재 컴포넌트에 전통css 파일을 호출하는 방법.

const iwidth = { maxWidth: '300px' } // 인라인 css 방식은 객체 형태이다. 
    // 조심할 점: max-width --> maxWidth 하이픈(-) 대신에 카멜표현식을 사용한다.
const myStyle = { color: "white" , backgroundColor: "DodgerBlue",
    padding: "10px" , fontFamily: "Verdana"
}

    // 정적파일: public 이하 경로만 지정한다.
    // 즉: /public/img/A.img --> /img/A.img 이렇게 public 없이 표기. 

export default function Exam1( props ){
    return(<>
        <h3> 스타일과 이미지 p.127 </h3>
        <ol>
            <li style={ { color: "#007fff" } }> 프론트엔드 </li>
            <ul>
                <li><img src="/img/bird1.png" style={ iwidth }/> </li>
                <li><img src={bird} style={ iwidth }/></li>
                <li><img src="https://placehold.co/600x400" style={ iwidth }/></li>
            </ul>
            <li className='backEnd'>백엔드</li>
            <ul>
                <li id="backEndSub">JAVA</li>
                <li class='warnings'>Oracle</li>
                <li style={myStyle}>JSP</li>
                <li>Spring Boot</li>
            </ul>
        </ol>
    </>)
}