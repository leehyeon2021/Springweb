import { NavLink } from "react-router-dom";

export default function TopNavi( prpos ){
    
    // jsx와 js 구분: 컴포넌트(함수) 내 return

    // 이곳은 js 형식. js 형식의 주석 처리

    // jsx에서 html에 없는 마크업들은 모두 컴포넌트이며 외부 호출시 import 한다.
    return(<> {/* 이곳은 jsx 형식. jsx 형식의 주석 처리 */}
        <nav> {/* nav 마크업은 HTML에 있는 문법 */}
            <NavLink to="/"> 생명주기 </NavLink> {/* HTML에 없는 마크업들은 import 필요. */}
            <NavLink to="/local"> 내부통신 </NavLink>
            <NavLink to="external"> 외부통신 </NavLink>
        </nav>
        <div>
            파이팅
        </div>
    </>)
}