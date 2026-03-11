/* p.117 , 컴포넌트 내보내기 하는 이유: 다른 jsx에서 해당하는 컴포넌트를 사용하기 위해.
부모 컴포넌트로 부터 상태변경함수를 props로 받아서 a클릭하면 상태를 front로 변경한다.
 */
export default function FrontComp( props ){ // .onSetMode은 부모가 전달한 함수명.
    return(<>
        <li>
            <a href="/" onClick={()=>{
            event.preventDefault(); {/* a마크업 클릭하면 페이지 이동하는 거 차단하는(새로고침x) 함수: event.preventDefault(); */}
            props.onSetMode('front');
            }}>프런트엔드</a>
            </li>
        <ul>
            <li>HTML5</li><li>CSS3</li><li>Javascript</li><li>jQuery</li>
        </ul>
    </>)
}