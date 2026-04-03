import { Route, Routes } from "react-router-dom";
import Login from "./pages/member/Login";
import Header from "./component/header";

export default function App( props ){
    return(
        <div id="wrap">
            {/* 헤더 */}
            <Header/>
            <Routes>
                {/* 본문들 - 무언가 만들면 라우터 추가부터 하기*/}
                <Route path="/member/login" element={ <Login/> }/>
            </Routes>
            {/* 푸터 */}
        </div>
    )
}