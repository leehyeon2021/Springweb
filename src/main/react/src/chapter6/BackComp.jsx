// 화살표함수: export, import는 마지막에 해야 함.
const BackComp = ({onMyEvent2}) => {
    return(<>
        <li><a href="/" onClick={onMyEvent2('백엔드 클릭됨(자식전달)')}>벡앤드</a></li>
        <ul>
            <li>java</li> <li>Oracle</li> <li>JSP</li> <li>Spring Boot</li>
        </ul>
    </>)
}
export default BackComp;