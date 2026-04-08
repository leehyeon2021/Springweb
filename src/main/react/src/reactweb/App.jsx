import { Route, Routes } from "react-router-dom";
import Login from "./pages/member/Login";
import Header from "./component/header";
import Write from "./pages/board/Write";
import Signup from "./pages/member/Signup";
import Board from "./pages/board/Board";
import View from "./pages/board/View";
import Chat from "./pages/chat/Chat";

export default function App( props ){
    return(
        <div id="wrap">
            {/* 헤더 */}
            <Header/>
            <Routes>
                {/* 본문들 - 무언가 만들면 라우터 추가부터 하기*/}
                <Route path="/member/Signup" element={ <Signup/> }/>
                <Route path="/member/login" element={ <Login/> }/>
                <Route path="/board/write" element={ <Write/> }/>
                <Route path="/board" element={ <Board/> }/>
                <Route path="/board/view" element={ <View/> }/>
                <Route path="/chat" element={< Chat/>}/>
            </Routes>
            {/* 푸터 */}
        </div>
    )
}