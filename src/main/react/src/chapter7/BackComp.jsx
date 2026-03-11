/*  p.117
부모 컴포넌트로 부터 상태변경함수를 props로 받아서 props{} 구조분해할당하여 a 클릭하면 상태를 back으로 수정한다.   */
const BackComp = ( {setMode} ) => {
    return(<>
        <li><a href="/" onClick={()=>{
            event.preventDefault(); {/* a마크업 클릭하면 페이지 이동하는 거 차단하는(새로고침x) 함수: event.preventDefault(); */}
            setMode('back');
            }}>벡앤드</a></li>
        <ul>
            <li>java</li> <li>Oracle</li> <li>JSP</li> <li>Spring Boot</li>
        </ul>
    </>)
}
export default BackComp;