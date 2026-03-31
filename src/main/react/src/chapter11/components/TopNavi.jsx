import { Link, NavLink } from "react-router-dom";

export default function TopNavi( props ){ // 상단메뉴/바. 헤더메뉴 컴포넌트 같은 거.

    // <a>: 클릭하면 브라우저 전체 새로고침( 깜빡임 있음)
    // <Link>: 클릭하면 새로고침 없이 URL 변경( 깜빡임 없음)

    return(<nav>
        <a href="/"> HOME </a>
        <NavLink to="/intro"> 인트로 </NavLink>
        <NavLink to="/intro/router"> Router관련Hook </NavLink>
        <Link to="/xyz"> 잘못된URL </Link>
    </nav>)
}